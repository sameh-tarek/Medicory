package com.sameh.medicory.service.admin.users.impl;

import com.sameh.medicory.entity.phoneEntities.UserPhoneNumber;
import com.sameh.medicory.entity.usersEntities.Hospital;
import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.exception.ConflictException;
import com.sameh.medicory.exception.RecordNotFoundException;
import com.sameh.medicory.mapper.HospitalMapper;
import com.sameh.medicory.mapper.UserMapper;
import com.sameh.medicory.model.users.hospital.HospitalDTO;
import com.sameh.medicory.model.users.hospital.HospitalResponseDTO;
import com.sameh.medicory.repository.HospitalRepository;
import com.sameh.medicory.repository.UserPhoneNumberRepository;
import com.sameh.medicory.repository.UserRepository;
import com.sameh.medicory.service.admin.users.AdminHospitalService;
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
    private final UserMapper userMapper;
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
    public String addHospital(HospitalDTO newHospital) {
        Hospital hospital = hospitalMapper.toEntity(newHospital);
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
            User updatedUser = userMapper.toEntity(updatedHospital.getUser());
            User oldUser = oldHospital.getUser();

            if (updatedUser != null) {
                oldUser.setEmail(updatedUser.getEmail());
                oldUser.setPassword(updatedUser.getPassword());
                oldUser.setEnabled(updatedUser.isEnabled());
                oldUser.setRole(updatedUser.getRole());
                oldUser.setUpdatedAt(LocalDateTime.now());
            }
            // Update or add user phone numbers
            List<UserPhoneNumber> updatedUserPhoneNumbers = updatedUser.getUserPhoneNumbers();
            List<UserPhoneNumber> existingUserPhoneNumbers = oldUser.getUserPhoneNumbers()
                    .stream()
                    .map(existingPhoneNumber -> {
                        Optional<UserPhoneNumber> matchingUpdatedPhoneNumber = updatedUserPhoneNumbers.stream()
                                .filter(updatedPhoneNumber ->
                                        updatedPhoneNumber.getId() == existingPhoneNumber.getId())
                                .findFirst();

                        if (matchingUpdatedPhoneNumber.isPresent()) {
                            UserPhoneNumber updatedPhoneNumber = matchingUpdatedPhoneNumber.get();
                            Optional<UserPhoneNumber> existingUser = userPhoneRepo.findUserByPhone(updatedPhoneNumber.getPhone());
                            if (existingUser.isPresent()) {
                                throw new ConflictException("This phone number " + updatedPhoneNumber.getPhone() + " already exists");
                            }
                            existingPhoneNumber.setPhone(updatedPhoneNumber.getPhone());
                        }
                        return existingPhoneNumber;
                    })
                    .collect(Collectors.toList());

            userPhoneRepo.saveAll(existingUserPhoneNumbers);
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
