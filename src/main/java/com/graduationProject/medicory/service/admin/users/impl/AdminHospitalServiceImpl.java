package com.graduationProject.medicory.service.admin.users.impl;

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
import com.graduationProject.medicory.service.admin.users.AdminHospitalService;
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
public class AdminHospitalServiceImpl implements AdminHospitalService {

    private final HospitalMapper hospitalMapper;

    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;
    private final UserPhoneNumberRepository userPhoneRepo;

    @Override
    public HospitalResponseDTO findHospitalByUserCode(String userCode) {
        Hospital hospital = hospitalRepository
                .findHospitalByUserCode(userCode)
                .orElseThrow(() -> new RecordNotFoundException("No Hospital with userCode " + userCode));
        return hospitalMapper.toResponseDTO(hospital);
    }

    @Override
    public HospitalResponseDTO findHospitalByEmail(String email) {
        Hospital hospital = hospitalRepository
                .findHospitalByUserEmail(email)
                .orElseThrow(() -> new RecordNotFoundException("No user *HOSPITAL* with email " + email));
        return hospitalMapper.toResponseDTO(hospital);
    }

    @Override
    public List<HospitalResponseDTO> findHospitalByName(String name) {
        List<Hospital> hospitals = hospitalRepository.findHospitalByName(name);
        if (!hospitals.isEmpty()) {
            return hospitals.stream()
                    .map(hospitalMapper::toResponseDTO)
                    .collect(Collectors.toList());
        }
        throw new RecordNotFoundException("No hospitals with name " + name);
    }

    @Override
    public HospitalDTO showAllDataOfHospitalById(long hospitalId) {
        if (hospitalId > 0) {
            Hospital hospital = hospitalRepository
                    .findById(hospitalId)
                    .orElseThrow(() -> new RecordNotFoundException("No hospital with id " + hospitalId));
            return hospitalMapper.toDTO(hospital);
        }
        throw new IllegalArgumentException("Invalid id ):");
    }

    @Override
    public String addHospital(HospitalRequestDTO newHospital) {
        Hospital hospital = hospitalMapper.toRequestEntity(newHospital);
        Optional<User> userExist = userRepository.findByEmail(hospital.getUser().getEmail());
        if (!userExist.isPresent()) {
            User newUser = hospital.getUser();
            newUser.setUpdatedAt(LocalDateTime.now());
            newUser.setCreatedAt(LocalDateTime.now());

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
            hospitalRepository.save(hospital);
            userPhoneRepo.saveAll(userPhoneNumbers);
            return "user inserted successfully";
        }
        throw new ConflictException("user email " + hospital.getUser().getEmail() + " already exist :)");
    }

    @Override
    public String updateHospital(Long hospitalId, HospitalDTO updatedHospital) {
        if (hospitalId > 0) {
            Hospital oldHospital = hospitalRepository
                    .findById(hospitalId)
                    .orElseThrow(() -> new RecordNotFoundException("No hospital with id " + hospitalId));

            oldHospital.setName(updatedHospital.getName());
            oldHospital.setAddress(updatedHospital.getAddress());
            oldHospital.setGoogleMapsLink(updatedHospital.getGoogleMapsLink());
            User oldUser = oldHospital.getUser();

            oldUser.setEmail(updatedHospital.getEmail());
            oldUser.setPassword(updatedHospital.getPassword());
            oldUser.setEnabled(updatedHospital.isEnabled());
            oldUser.setRole(updatedHospital.getRole());
            oldUser.setUpdatedAt(LocalDateTime.now());
            List<String> updatedPhoneNumbers = updatedHospital.getUserPhoneNumbers();
            List<UserPhoneNumber> oldUserPhoneNumbers = oldUser.getUserPhoneNumbers();

            for (int i = 0; i < updatedPhoneNumbers.size(); i++) {
                String updatedPhoneNumber = updatedPhoneNumbers.get(i);
                UserPhoneNumber userPhoneNumber = oldUserPhoneNumbers.get(i);

                if (!userPhoneNumber.getPhone().equals(updatedPhoneNumber)) {
                    Optional<UserPhoneNumber> existingUser = userPhoneRepo.findUserByPhone(updatedPhoneNumber);
                    if (existingUser.isPresent()) {
                        throw new ConflictException("This phone number " + updatedPhoneNumber + " already exists");
                    }
                    userPhoneNumber.setPhone(updatedPhoneNumber);
                }
            }


            userPhoneRepo.saveAll(oldUserPhoneNumbers);
            userRepository.save(oldUser);
            hospitalRepository.save(oldHospital);
            return "Updated successfully";
        }
        throw new IllegalArgumentException("Invalid id :(");
    }

    @Override
    public String deleteHospital(long hospitalId) {
        if (hospitalId > 0) {
            Hospital hospital = hospitalRepository.findById(hospitalId)
                    .orElseThrow(() -> new RecordNotFoundException("No hospital with id " + hospitalId));
            User user = hospital.getUser();
            if (user.isEnabled()) {
                user.setEnabled(false);
                userRepository.save(user);
            }
            return "deleted";
        }
        throw new IllegalArgumentException("invalid id ");
    }
}
