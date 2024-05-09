package com.graduationProject.medicory.service.admin.users.impl;

import com.graduationProject.medicory.entity.phoneEntities.UserPhoneNumber;
import com.graduationProject.medicory.entity.usersEntities.Clinic;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.exception.ConflictException;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.exception.UserDisabledException;
import com.graduationProject.medicory.mapper.ClinicMapper;
import com.graduationProject.medicory.mapper.UserMapper;
import com.graduationProject.medicory.model.users.UserDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicRequestDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicResponseDTO;
import com.graduationProject.medicory.repository.ClinicRepository;
import com.graduationProject.medicory.repository.UserPhoneNumberRepository;
import com.graduationProject.medicory.repository.UserRepository;
import com.graduationProject.medicory.service.admin.users.AdminClinicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor

//TODO enhance this
public class AdminClinicServiceImpl implements AdminClinicService {

    private final ClinicRepository clinicRepository;
    private final UserRepository userRepository;
    private final ClinicMapper map;
    private final UserMapper userMapper;
    private final UserPhoneNumberRepository userPhoneRepo;


    @Override
    public ClinicResponseDTO findClinicByUserCode(String userCode) {
        Clinic clinic = clinicRepository.findByUserCode(userCode)
                .orElseThrow(() -> new RecordNotFoundException("No user *CLINIC* has code : " + userCode));
        return map.toResponseDTO(clinic);
    }

    @Override
    public List<ClinicResponseDTO> findClinicsByName(String name) {

        List<Clinic> clinics = clinicRepository.findByName(name);
        if (!clinics.isEmpty()) {
            return clinics.stream()
                    .map(map::toResponseDTO)
                    .collect(Collectors.toList());
        } else {
            throw new RecordNotFoundException("No clinics with this name : " + name);
        }
    }

    @Override
    public ClinicResponseDTO findClinicByUserEmail(String userEmail) {
        Clinic clinic = clinicRepository
                .findByUserEmail(userEmail)
                .orElseThrow(() -> new RecordNotFoundException("No user *CLINIC* with email " + userEmail));
        return map.toResponseDTO(clinic);
    }

    @Override
    public ClinicDTO showAllDataOfClinicByClinicId(long clinicId) {
        if (clinicId > 0) {
            Clinic clinic = clinicRepository.findById(clinicId)
                    .orElseThrow(() -> new RecordNotFoundException("No clinic with id " + clinicId));
            return map.toDto(clinic);
        }
        throw new IllegalArgumentException("Invalid id " + clinicId);
    }


    @Override
    public String addNewClinic(ClinicRequestDTO clinicRequestDTO) {
        Clinic newClinic = map.toRequestEntity(clinicRequestDTO);
        User newUser = newClinic.getUser();
        Optional<User> exsistingUser = userRepository.findByEmail(newUser.getEmail());
        if (!exsistingUser.isPresent()) {

            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());
            // user phone numbers
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
            return "Clinic added successfully";
        }
        throw new ConflictException("The user email " + newUser.getEmail() + " already exist");

    }

    @Override
    public String updateClinic(ClinicDTO updatedClinic, Long clinicId) {
        if (clinicId > 0) {
            Clinic clinic = clinicRepository.findById(clinicId)
                    .orElseThrow(() -> new RecordNotFoundException("No Clinic with id " + clinicId));

            clinic.setName(updatedClinic.getName());
            clinic.setGoogleMapsLink(updatedClinic.getGoogleMapsLink());
            clinic.setOwnerName(updatedClinic.getOwnerName());
            clinic.setSpecialization(updatedClinic.getSpecialization());

            UserDTO updatedUser = updatedClinic.getUser();
            User oldUser = clinic.getUser();
            if (updatedUser != null) {
                oldUser.setEmail(updatedUser.getEmail());
                oldUser.setPassword(updatedUser.getPassword());
                oldUser.setRole(updatedUser.getRole());
                oldUser.setEnabled(updatedUser.isEnabled());
                oldUser.setUpdatedAt(LocalDateTime.now());
            }
            // Update or add user phone numbers
            List<UserPhoneNumber> updatedUserPhoneNumbers = userMapper.toEntity(updatedUser).getUserPhoneNumbers();
            List<UserPhoneNumber> existingUserPhoneNumbers = oldUser.getUserPhoneNumbers()
                    .stream()
                    .map(existingPhoneNumber -> {
                        Optional<UserPhoneNumber> matchingUpdatedPhoneNumber = updatedUserPhoneNumbers.stream()
                                .filter(updatedPhoneNumber ->
                                        updatedPhoneNumber.getId() == existingPhoneNumber.getId())
                                .findFirst();

                        if (matchingUpdatedPhoneNumber.isPresent()) {
                            UserPhoneNumber updatedPhoneNumber = matchingUpdatedPhoneNumber.get();
                            // updated !
                            if (!existingPhoneNumber.getPhone().equals(updatedPhoneNumber.getPhone())) {
                                Optional<UserPhoneNumber> existingUser = userPhoneRepo.findUserByPhone(updatedPhoneNumber.getPhone());
                                if (existingUser.isPresent()) {
                                    throw new ConflictException("This phone number " + updatedPhoneNumber.getPhone() + " already exists");
                                }
                                existingPhoneNumber.setPhone(updatedPhoneNumber.getPhone());
                            }
                        }
                        return existingPhoneNumber;
                    })
                    .collect(Collectors.toList());


            userPhoneRepo.saveAll(existingUserPhoneNumbers);
            userRepository.save(oldUser);
            clinicRepository.save(clinic);
            return "Clinic updted sucessfully";
        }

        throw new IllegalArgumentException("Invalid id " + clinicId);
    }

    @Override
    public String deleteClinicById(Long clinicId) {
        if (clinicId > 0) {
            Clinic clinic = clinicRepository.findById(clinicId)
                    .orElseThrow(() -> new RecordNotFoundException("No Clinic with id " + clinicId));

            User unEnabledUser = clinic.getUser();
            if (unEnabledUser.isEnabled()) {
                unEnabledUser.setEnabled(false);
                unEnabledUser.setUpdatedAt(LocalDateTime.now());
                userRepository.save(unEnabledUser);
                return "Clinic deleted successfully";
            }
            throw new UserDisabledException("This user is unEnabled already");
        }
        throw new IllegalArgumentException("Invalid id");


    }
}
