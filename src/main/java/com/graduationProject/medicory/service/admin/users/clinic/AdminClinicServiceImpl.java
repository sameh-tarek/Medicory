package com.graduationProject.medicory.service.admin.users.clinic;

import com.graduationProject.medicory.entity.phoneEntities.UserPhoneNumber;
import com.graduationProject.medicory.entity.usersEntities.Clinic;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.exception.ConflictException;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.exception.UserDisabledException;
import com.graduationProject.medicory.mapper.usersMappers.ClinicMapper;
import com.graduationProject.medicory.model.users.clinic.ClinicDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicRequestDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicResponseDTO;
import com.graduationProject.medicory.repository.phoneRepositories.UserPhoneNumberRepository;
import com.graduationProject.medicory.repository.usersRepositories.ClinicRepository;
import com.graduationProject.medicory.repository.usersRepositories.UserRepository;
import com.graduationProject.medicory.utils.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminClinicServiceImpl implements AdminClinicService {

    private final ClinicRepository clinicRepository;
    private final UserRepository userRepository;
    private final ClinicMapper map;
    private final UserPhoneNumberRepository userPhoneRepo;
    private final PasswordEncoder passwordEncoder;
    private final PasswordGenerator passwordGenerator;

    @Override
    public ClinicResponseDTO findClinicByUserCode(String userCode) {
        log.info("Admin search for clinic with code {}", userCode);
        Clinic clinic = clinicRepository.findByUserCode(userCode)
                .orElseThrow(() -> new RecordNotFoundException("No user *CLINIC* has code : " + userCode));
        ClinicResponseDTO response = map.toResponseDTO(clinic);
        log.info(" Data of clinic with code {} :{} ", userCode, response);
        return response;
    }

    @Override
    public List<ClinicResponseDTO> findClinicsByName(String name) {

        List<Clinic> clinics = clinicRepository.findByName(name);
        log.info("Admin search for clinic with name {}", name);
        if (!clinics.isEmpty()) {
            List<ClinicResponseDTO> response = clinics
                    .stream()
                    .map(map::toResponseDTO)
                    .collect(Collectors.toList());
            log.info(" Data of clinic with name {} :{} ", name, response);
            return response;
        } else {
            throw new RecordNotFoundException("No clinics with this name : " + name);
        }
    }

    @Override
    public ClinicResponseDTO findClinicByUserEmail(String userEmail) {
        log.info("Admin search for clinic with email {}", userEmail);
        Clinic clinic = clinicRepository
                .findByUserEmail(userEmail)
                .orElseThrow(() -> new RecordNotFoundException("No user *CLINIC* with email " + userEmail));
        ClinicResponseDTO response = map.toResponseDTO(clinic);
        log.info(" Data of clinic with email {} :{} ", userEmail, response);

        return response;
    }

    @Override
    public ClinicDTO showAllDataOfClinicByClinicId(long clinicId) {
        log.info("Admin get all data of clinic with id {}", clinicId);
        if (clinicId > 0) {
            Clinic clinic = clinicRepository.findById(clinicId)
                    .orElseThrow(() -> new RecordNotFoundException("No clinic with id " + clinicId));
            ClinicDTO response = map.toDto(clinic);
            response.setPassword(null);
            return response;
        }
        throw new IllegalArgumentException("Invalid id " + clinicId);
    }


    @Override
    public String addNewClinic(ClinicRequestDTO clinicDTO) {
        log.info("Admin add new clinic ");
        Clinic newClinic = map.toRequestEntity(clinicDTO);
        User newUser = newClinic.getUser();

        Optional<User> existingUser = userRepository.findByEmail(newUser.getEmail());
        if (!existingUser.isPresent()) {
            String password = passwordGenerator.generatePassword();

            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());
            newUser.setPassword(passwordEncoder.encode(password));

            List<UserPhoneNumber> userPhoneNumbers = newUser.getUserPhoneNumbers()
                    .stream()
                    .map(userPhoneNumber -> {
                        Optional<UserPhoneNumber> user = userPhoneRepo.findUserByPhone(userPhoneNumber.getPhone());
                        if (user.isPresent())
                            throw new ConflictException("This phone number " + userPhoneNumber.getPhone() + " already exist");

                        userPhoneNumber.setUser(newUser);
                        return userPhoneNumber;
                    })
                    .collect(Collectors.toList());

            userRepository.save(newUser);
            clinicRepository.save(newClinic);
            userPhoneRepo.saveAll(userPhoneNumbers);

            passwordGenerator.sendPasswordEmail(newUser.getEmail(), password);
            log.info("Clinic added successfully");
            return "Clinic added successfully";
        }
        log.error("The user email {} already exist", newUser.getEmail());
        throw new ConflictException("The user email " + newUser.getEmail() + " already exist");

    }

    @Override
    public String updateClinic(ClinicDTO updatedClinic, Long clinicId) {
        if (clinicId > 0) {
            log.trace("Admin update clinic with id {}" + clinicId);
            Clinic clinic = clinicRepository.findById(clinicId)
                    .orElseThrow(() -> new RecordNotFoundException("No Clinic with id " + clinicId));

            clinic.setName(updatedClinic.getName());
            clinic.setGoogleMapsLink(updatedClinic.getGoogleMapsLink());
            clinic.setOwnerName(updatedClinic.getOwnerName());
            clinic.setAddress(updatedClinic.getAddress());
            clinic.setSpecialization(updatedClinic.getSpecialization());

            User oldUser = clinic.getUser();
            List<String> updatedPhoneNumbers = updatedClinic.getUserPhoneNumbers();
            List<UserPhoneNumber> oldUserPhoneNumbers = oldUser.getUserPhoneNumbers();

            for (int i = 0; i < updatedPhoneNumbers.size(); i++) {
                String updatedPhoneNumber = updatedPhoneNumbers.get(i);
                UserPhoneNumber userPhoneNumber = oldUserPhoneNumbers.get(i);

                if (!userPhoneNumber.getPhone().equals(updatedPhoneNumber)) {
                    Optional<UserPhoneNumber> existingUser = userPhoneRepo.findUserByPhone(updatedPhoneNumber);
                    if (existingUser.isPresent()) {
                        log.error("This phone number {} already exist", updatedPhoneNumber);
                        throw new ConflictException("This phone number " + updatedPhoneNumber + " already exists");
                    }
                    userPhoneNumber.setPhone(updatedPhoneNumber);
                }
            }

            oldUser.setEmail(updatedClinic.getEmail());
            oldUser.setRole(updatedClinic.getRole());
            oldUser.setEnabled(updatedClinic.isEnabled());
            oldUser.setUpdatedAt(LocalDateTime.now());
            if (updatedClinic.getPassword() != null) {
                String newPassword = updatedClinic.getPassword();
                oldUser.setPassword(passwordEncoder.encode(newPassword));
                passwordGenerator.sendPasswordEmail(updatedClinic.getEmail(), newPassword);
            }

            userRepository.save(oldUser);
            clinicRepository.save(clinic);
            userPhoneRepo.saveAll(oldUserPhoneNumbers);
            log.info("clinic updated successfully");
            return "Clinic updated successfully";
        }

        throw new IllegalArgumentException("Invalid id " + clinicId);
    }

    @Override
    public String deleteClinicById(Long clinicId) {
        if (clinicId > 0) {
            Clinic clinic = clinicRepository.findById(clinicId)
                    .orElseThrow(() -> new RecordNotFoundException("No Clinic with id " + clinicId));

            log.info("Admin is trying disable clinic with id {}", clinicId);
            User unEnabledUser = clinic.getUser();
            if (unEnabledUser.isEnabled()) {
                unEnabledUser.setEnabled(false);
                unEnabledUser.setUpdatedAt(LocalDateTime.now());
                userRepository.save(unEnabledUser);
                log.info("Clinic with id {} deleted successfully", clinicId);
                return "Clinic deleted successfully";
            }
            log.error("Admin try to disable clinic that is already disabled");
            throw new UserDisabledException("This user is unEnabled already");
        }
        throw new IllegalArgumentException("Invalid id");


    }
}
