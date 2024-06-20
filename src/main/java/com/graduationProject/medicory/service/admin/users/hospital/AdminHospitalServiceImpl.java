package com.graduationProject.medicory.service.admin.users.hospital;

import com.graduationProject.medicory.entity.phoneEntities.UserPhoneNumber;
import com.graduationProject.medicory.entity.usersEntities.Hospital;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.exception.ConflictException;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.mapper.usersMappers.HospitalMapper;
import com.graduationProject.medicory.model.users.hospital.HospitalDTO;
import com.graduationProject.medicory.model.users.hospital.HospitalRequestDTO;
import com.graduationProject.medicory.model.users.hospital.HospitalResponseDTO;
import com.graduationProject.medicory.repository.usersRepositories.HospitalRepository;
import com.graduationProject.medicory.repository.phoneRepositories.UserPhoneNumberRepository;
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
public class AdminHospitalServiceImpl implements AdminHospitalService {

    private final HospitalMapper hospitalMapper;
    private final PasswordEncoder passwordEncoder;
    private final PasswordGenerator passwordGenerator;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;
    private final UserPhoneNumberRepository userPhoneRepo;

    @Override
    public HospitalResponseDTO findHospitalByUserCode(String userCode) {
        log.info("Admin search for hospital with code {}",userCode);
        Hospital hospital = hospitalRepository
                .findHospitalByUserCode(userCode)
                .orElseThrow(() -> new RecordNotFoundException("No Hospital with userCode " + userCode));
        HospitalResponseDTO response = hospitalMapper.toResponseDTO(hospital);
        log.info("Hospital with code {} : {}",userCode,response);
        return response;
    }

    @Override
    public HospitalResponseDTO findHospitalByEmail(String email) {
        log.info("Admin search for hospital with email {}",email);
        Hospital hospital = hospitalRepository
                .findHospitalByUserEmail(email)
                .orElseThrow(() -> new RecordNotFoundException("No user *HOSPITAL* with email " + email));
        HospitalResponseDTO response = hospitalMapper.toResponseDTO(hospital);
        log.info("Hospital with email {} : {}",email,response);
        return response;
    }

    @Override
    public List<HospitalResponseDTO> findHospitalByName(String name) {
        log.info("Admin search for hospital with name {}",name);
        List<Hospital> hospitals = hospitalRepository.findHospitalByName(name);
        if (!hospitals.isEmpty()) {
           List<HospitalResponseDTO> response =hospitals
                   .stream()
                    .map(hospitalMapper::toResponseDTO)
                    .collect(Collectors.toList());

            log.info("Hospital with name {} : {}",name,response);
            return response;
        }
        log.error("No hospitals with name {}",name);
        throw new RecordNotFoundException("No hospitals with name " + name);
    }

    @Override
    public HospitalDTO showAllDataOfHospitalById(long hospitalId) {
        log.info("Admin want to get data of hospital with id {}",hospitalId);
        if (hospitalId > 0) {
            Hospital hospital = hospitalRepository
                    .findById(hospitalId)
                    .orElseThrow(() -> new RecordNotFoundException("No hospital with id " + hospitalId));
            HospitalDTO response = hospitalMapper.toDTO(hospital);
            response.setPassword(null);
            log.info("Data of hospital with id {} : {}",hospitalId,response);
            return response;
        }
        throw new IllegalArgumentException("Invalid id ):");
    }

    @Override
    public String addHospital(HospitalRequestDTO newHospital) {
        log.info("Admin want to add new hospital");
        Hospital hospital = hospitalMapper.toRequestEntity(newHospital);
        Optional<User> userExist = userRepository.findByEmail(hospital.getUser().getEmail());
        if (!userExist.isPresent()) {
            User newUser = hospital.getUser();
            String password= passwordGenerator.generatePassword();
            newUser.setPassword(password);
            newUser.setUpdatedAt(LocalDateTime.now());
            newUser.setCreatedAt(LocalDateTime.now());

            // user phone numbers
            List<UserPhoneNumber> userPhoneNumbers = newUser.getUserPhoneNumbers()
                    .stream()
                    .map(userPhoneNumber -> {
                        Optional<UserPhoneNumber> user = userPhoneRepo.findUserByPhone(userPhoneNumber.getPhone());
                        if (user.isPresent()) {
                            log.error("Admin tried to add phone number {} that already exists",userPhoneNumber.getPhone());
                            throw new ConflictException("This phone number " + userPhoneNumber.getPhone() + " already exist");
                        }
                        userPhoneNumber.setUser(newUser);
                        return userPhoneNumber;
                    })
                    .collect(Collectors.toList());

            userRepository.save(newUser);
            hospitalRepository.save(hospital);
            userPhoneRepo.saveAll(userPhoneNumbers);
            passwordGenerator.sendPasswordEmail(newUser.getEmail(), password);
            log.info("Hospital added successfully");
            return "Hospital added successfully";
        }
        throw new ConflictException("user email " + hospital.getUser().getEmail() + " already exist :)");
    }

    @Override
    public String updateHospital(Long hospitalId, HospitalDTO updatedHospital) {
        log.trace("Admin try to update hospital with id {}",hospitalId);
        if (hospitalId > 0) {
            Hospital oldHospital = hospitalRepository
                    .findById(hospitalId)
                    .orElseThrow(() -> new RecordNotFoundException("No hospital with id " + hospitalId));

            oldHospital.setName(updatedHospital.getName());
            oldHospital.setAddress(updatedHospital.getAddress());
            oldHospital.setGoogleMapsLink(updatedHospital.getGoogleMapsLink());
            User oldUser = oldHospital.getUser();

            List<String> updatedPhoneNumbers = updatedHospital.getUserPhoneNumbers();
            List<UserPhoneNumber> oldUserPhoneNumbers = oldUser.getUserPhoneNumbers();

            for (int i = 0; i < updatedPhoneNumbers.size(); i++) {
                String updatedPhoneNumber = updatedPhoneNumbers.get(i);
                UserPhoneNumber userPhoneNumber = oldUserPhoneNumbers.get(i);

                if (!userPhoneNumber.getPhone().equals(updatedPhoneNumber)) {
                    Optional<UserPhoneNumber> existingUser = userPhoneRepo.findUserByPhone(updatedPhoneNumber);
                    if (existingUser.isPresent()) {
                        log.error("Admin try to update phone number with existing phone number {}",updatedPhoneNumber);
                        throw new ConflictException("This phone number " + updatedPhoneNumber + " already exists");
                    }
                    userPhoneNumber.setPhone(updatedPhoneNumber);
                }
            }

            oldUser.setEmail(updatedHospital.getEmail());
            oldUser.setEnabled(updatedHospital.isEnabled());
            oldUser.setRole(updatedHospital.getRole());
            oldUser.setUpdatedAt(LocalDateTime.now());
            if(updatedHospital.getPassword()!=null){
                String newPassword = updatedHospital.getPassword();
                oldUser.setPassword(passwordEncoder.encode(newPassword));
                passwordGenerator.sendPasswordEmail(updatedHospital.getEmail(),newPassword);
            }

            userPhoneRepo.saveAll(oldUserPhoneNumbers);
            userRepository.save(oldUser);
            hospitalRepository.save(oldHospital);
            log.info("Hospital updated successfully");
            return "Hospital updated successfully" ;
        }
        throw new IllegalArgumentException("Invalid id :(");
    }

    @Override
    public String deleteHospital(long hospitalId) {
        log.info("Admin try to disable hospital with id {}",hospitalId);
        if (hospitalId > 0) {
            Hospital hospital = hospitalRepository.findById(hospitalId)
                    .orElseThrow(() -> new RecordNotFoundException("No hospital with id " + hospitalId));
            User user = hospital.getUser();
            if (user.isEnabled()) {
                user.setEnabled(false);
                userRepository.save(user);
            }
            log.info("Hospital with id {} successfully disabled",hospitalId);
            return "Hospital deleted successfully";
        }
        throw new IllegalArgumentException("invalid id ");
    }
}
