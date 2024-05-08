package com.graduationProject.medicory.service.admin.users.impl;

import com.graduationProject.medicory.entity.phoneEntities.UserPhoneNumber;
import com.graduationProject.medicory.entity.usersEntities.Doctor;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.exception.ConflictException;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.exception.UserDisabledException;
import com.graduationProject.medicory.mapper.DoctorMapper;
import com.graduationProject.medicory.mapper.UserMapper;
import com.graduationProject.medicory.model.users.doctor.DoctorRequestDTO;
import com.graduationProject.medicory.model.users.doctor.DoctorResponseDTO;
import com.graduationProject.medicory.repository.DoctorRepository;
import com.graduationProject.medicory.repository.UserPhoneNumberRepository;
import com.graduationProject.medicory.repository.UserRepository;
import com.graduationProject.medicory.service.admin.users.AdminDoctorService;
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

public class AdminDoctorServiceImpl implements AdminDoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final DoctorMapper doctorMapper;
    private final UserMapper userMapper;
    private final UserPhoneNumberRepository userPhoneRepo;


    @Override
    public DoctorRequestDTO showAllDoctorDataById(Long doctorId) {
        if (doctorId > 0) {
            Doctor doctor = doctorRepository.findById(doctorId)
                    .orElseThrow(() -> new RecordNotFoundException("No doctor  with id " + doctorId));
            return doctorMapper.toDTO(doctor);
        }
        throw new IllegalArgumentException("Invalid id " + doctorId + ".....");
    }

    @Override
    public DoctorResponseDTO findDoctoByUserEmail(String userEmail) {
        Doctor doctor = doctorRepository
                .findDoctorByUserEmail(userEmail)
                .orElseThrow(() -> new RecordNotFoundException("No user *DOCTOR* with this email " + userEmail));
        return doctorMapper.toResponseDTO(doctor);
    }

    @Override
    public DoctorResponseDTO findDoctorByUserCode(String userCode) {
        Doctor doctor = doctorRepository
                .findDoctorByUserCode(userCode)
                .orElseThrow(() -> new RecordNotFoundException("No user *DOCTOR* with code : " + userCode));
        return doctorMapper.toResponseDTO(doctor);
    }

    //:(
    @Override
    public List<DoctorResponseDTO> findDoctorbyFullName(String fullName) {
        List<Doctor> doctors = null;
        if (fullName.contains(" ")) {
            String[] nameParts = fullName.split(" ");
            if (nameParts.length == 1) {
                //fName
                doctors = doctorRepository.findDoctorByFirstName(nameParts[0]);
                doctors.addAll(doctorRepository.findDoctorByMiddleName(nameParts[0]));
                doctors.addAll(doctorRepository.findDoctorByLastName(nameParts[0]));
            } else if (nameParts.length == 2) {
                // fName ,mName
                doctors = doctorRepository.findDoctorByFirstNameAndMiddleName(nameParts[0], nameParts[1]);
                if (doctors.isEmpty()) {
                    //fName, lName
                    doctors = doctorRepository.findDoctorByFirstNameAndLastName(nameParts[0], nameParts[1]);
                    if (doctors.isEmpty()) {
                        //mName ,lName
                        doctors = doctorRepository.findDoctorByMiddleNameAndLastName(nameParts[0], nameParts[1]);
                    }
                }
            } else if (nameParts.length == 3) {
                doctors = doctorRepository.findDoctorByFirstNameAndMiddleNameAndLastName(nameParts[0], nameParts[1], nameParts[2]);
            }

        } else {
            doctors = doctorRepository.findDoctorByFirstName(fullName);
            doctors.addAll(doctorRepository.findDoctorByMiddleName(fullName));
            doctors.addAll(doctorRepository.findDoctorByLastName(fullName));
        }
        if (doctors.isEmpty())
            throw new RecordNotFoundException("No doctor found with name " + fullName);

        return doctors.stream()
                .map(doctorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String addNewDoctor(DoctorRequestDTO newDoctorRequestDTO) {
        Doctor newDoctor = doctorMapper.toEntity(newDoctorRequestDTO);
        User newUser = newDoctor.getUser();
        Optional<User> existing = userRepository.findByEmail(newUser.getEmail());
        if (!existing.isPresent()) {
            newUser.setUpdatedAt(LocalDateTime.now());
            newUser.setCreatedAt(LocalDateTime.now());

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
            doctorRepository.save(newDoctor);
            userPhoneRepo.saveAll(userPhoneNumbers);
            return "Doctor added successfully";
        }
        throw new ConflictException("User with this email " + newUser.getEmail() + " already exist ");
    }

    //TODO enhance update function for all users
    @Override
    public String updateDoctor(DoctorRequestDTO updatedDoctorRequestDTO, Long doctorId) {
        if (doctorId > 0) {
            Doctor oldDoctor = doctorRepository.findById(doctorId)
                    .orElseThrow(() -> new RecordNotFoundException("No doctor with id " + doctorId));

            oldDoctor.setFirstName(updatedDoctorRequestDTO.getFirstName());
            oldDoctor.setMiddleName(updatedDoctorRequestDTO.getMiddleName());
            oldDoctor.setLastName(updatedDoctorRequestDTO.getLastName());
            oldDoctor.setSpecialization(updatedDoctorRequestDTO.getSpecialization());
            oldDoctor.setLicenceNumber(updatedDoctorRequestDTO.getLicenceNumber());
            oldDoctor.setNationalId(updatedDoctorRequestDTO.getNationalId());
            oldDoctor.setMaritalStatus(updatedDoctorRequestDTO.getMaritalStatus());
            oldDoctor.setGender(updatedDoctorRequestDTO.getGender());

            User updatedUser = userMapper.toEntity(updatedDoctorRequestDTO.getUser());
            User oldUser = oldDoctor.getUser();

            if (updatedUser != null) {
                oldUser.setEmail(updatedUser.getEmail());
                oldUser.setPassword(updatedUser.getPassword());
                oldUser.setEnabled(updatedUser.isEnabled());
                oldUser.setRole(updatedUser.getRole());
                oldUser.setUpdatedAt(LocalDateTime.now());
            }

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
            doctorRepository.save(oldDoctor);
            return "Doctor updated  successfully";

        } else
            throw new IllegalArgumentException("Invalid id " + doctorId + ".....");
    }

    @Override
    public String deleteDoctorById(Long doctorId) {
        if (doctorId > 0) {
            Doctor doctor = doctorRepository.findById(doctorId)
                    .orElseThrow(() -> new RecordNotFoundException("No doctor with id " + doctorId));

            User unEnabledUser = doctor.getUser();
            if (unEnabledUser.isEnabled()) {
                unEnabledUser.setUpdatedAt(LocalDateTime.now());
                unEnabledUser.setEnabled(false);
                userRepository.save(unEnabledUser);
                return "Doctor deleted successfully";
            }
            throw new UserDisabledException("This user is unEnabled already");
        }
        throw new IllegalArgumentException("Invalid id " + doctorId + ".....");
    }
}
