package com.graduationProject.medicory.service.admin.users.impl;

import com.graduationProject.medicory.entity.phoneEntities.UserPhoneNumber;
import com.graduationProject.medicory.entity.usersEntities.Doctor;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.exception.ConflictException;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.exception.UserDisabledException;
import com.graduationProject.medicory.mapper.DoctorMapper;
import com.graduationProject.medicory.model.users.doctor.DoctorDTO;
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
    private final UserPhoneNumberRepository userPhoneRepo;

    @Override
    public DoctorResponseDTO findDoctoByUserEmail(String userEmail) {
        log.info("Admin search for doctor by email {} ",userEmail);
        Doctor doctor = doctorRepository
                .findDoctorByUserEmail(userEmail)
                .orElseThrow(() -> new RecordNotFoundException("No user *DOCTOR* with this email " + userEmail));
       DoctorResponseDTO response = doctorMapper.toResponseDTO(doctor);
        log.info("Data of doctor with email {} : {}",userEmail,response);
        return  response;
    }

    @Override
    public DoctorResponseDTO findDoctorByUserCode(String userCode) {
        log.info("Admin search for doctor by code {} ",userCode);

        Doctor doctor = doctorRepository
                .findDoctorByUserCode(userCode)
                .orElseThrow(() -> new RecordNotFoundException("No user *DOCTOR* with code : " + userCode));
        DoctorResponseDTO response = doctorMapper.toResponseDTO(doctor);
        log.info("Data of doctor with code {} : {}",userCode,response);
        return response ;
    }

    @Override
    public List<DoctorResponseDTO> findDoctorbyFullName(String fullName) {
        List<Doctor> doctors = null;
        log.info("Admin search for doctor by fullName {} ",fullName);
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
        if (doctors.isEmpty()) {
            log.error("No doctor found with name {}" ,fullName );
            throw new RecordNotFoundException("No doctor found with name " + fullName);
        }
       List<DoctorResponseDTO> response = doctors
               .stream()
                .map(doctorMapper::toResponseDTO)
                .collect(Collectors.toList());
        log.info("Data of doctor with name {} : {}",fullName,response);
       return response ;
    }
    @Override
    public DoctorDTO showAllDoctorDataById(Long doctorId) {
        if (doctorId > 0) {
            log.info("Admin want to get data of doctor with id {}",doctorId);
            Doctor doctor = doctorRepository.findById(doctorId)
                    .orElseThrow(() -> new RecordNotFoundException("No doctor  with id " + doctorId));
            DoctorDTO response = doctorMapper.toDTO(doctor);
            log.info("Data of doctor with id {} :{}",doctorId,response);
            return response;
        }
        throw new IllegalArgumentException("Invalid id " + doctorId + ".....");
    }
    @Override
    public String addNewDoctor(DoctorRequestDTO newDoctorDTO) {
        log.info("Admin want to add new doctor");
        Doctor newDoctor = doctorMapper.toRequestEntity(newDoctorDTO);
        User newUser = newDoctor.getUser();
        Optional<User> existing = userRepository.findByEmail(newUser.getEmail());
        if (!existing.isPresent()) {
            newUser.setUpdatedAt(LocalDateTime.now());
            newUser.setCreatedAt(LocalDateTime.now());

            List<UserPhoneNumber> userPhoneNumbers = newUser.getUserPhoneNumbers()
                    .stream()
                    .map(userPhoneNumber -> {
                        Optional<UserPhoneNumber> user = userPhoneRepo.findUserByPhone(userPhoneNumber.getPhone());
                        if (user.isPresent()) {
                            log.error("Admin add phone number {} already exist",userPhoneNumber.getPhone());
                            throw new ConflictException("This phone number " + userPhoneNumber.getPhone() + " already exist");
                        }
                        userPhoneNumber.setUser(newUser);
                        return userPhoneNumber;
                    })
                    .collect(Collectors.toList());

            userRepository.save(newUser);
            doctorRepository.save(newDoctor);
            userPhoneRepo.saveAll(userPhoneNumbers);
            log.info("Doctor added successfully");
            return "Doctor added successfully";
        }
        throw new ConflictException("User with this email " + newUser.getEmail() + " already exist ");
    }

    @Override
    public String updateDoctor(DoctorDTO updatedDoctorDTO, Long doctorId) {
        log.trace("Admin want to update doctor with id {}",doctorId);
        if (doctorId > 0) {
            Doctor oldDoctor = doctorRepository.findById(doctorId)
                    .orElseThrow(() -> new RecordNotFoundException("No doctor with id " + doctorId));

            oldDoctor.setFirstName(updatedDoctorDTO.getFirstName());
            oldDoctor.setMiddleName(updatedDoctorDTO.getMiddleName());
            oldDoctor.setLastName(updatedDoctorDTO.getLastName());
            oldDoctor.setSpecialization(updatedDoctorDTO.getSpecialization());
            oldDoctor.setLicenceNumber(updatedDoctorDTO.getLicenceNumber());
            oldDoctor.setNationalId(updatedDoctorDTO.getNationalId());
            oldDoctor.setMaritalStatus(updatedDoctorDTO.getMaritalStatus());
            oldDoctor.setGender(updatedDoctorDTO.getGender());

            User oldUser = oldDoctor.getUser();

            oldUser.setEmail(updatedDoctorDTO.getEmail());
            oldUser.setPassword(updatedDoctorDTO.getPassword());
            oldUser.setEnabled(updatedDoctorDTO.isEnabled());
            oldUser.setRole(updatedDoctorDTO.getRole());
            oldUser.setUpdatedAt(LocalDateTime.now());

            List<String> updatedPhoneNumbers = updatedDoctorDTO.getUserPhoneNumbers();
            List<UserPhoneNumber> oldUserPhoneNumbers = oldUser.getUserPhoneNumbers();

            for (int i = 0; i < updatedPhoneNumbers.size(); i++) {
                String updatedPhoneNumber = updatedPhoneNumbers.get(i);
                UserPhoneNumber userPhoneNumber = oldUserPhoneNumbers.get(i);

                if (!userPhoneNumber.getPhone().equals(updatedPhoneNumber)) {
                    Optional<UserPhoneNumber> existingUser = userPhoneRepo.findUserByPhone(updatedPhoneNumber);
                    if (existingUser.isPresent()) {
                        log.error("Admin add phone number {} already exist",userPhoneNumber.getPhone());
                        throw new ConflictException("This phone number " + updatedPhoneNumber + " already exists");
                    }
                    userPhoneNumber.setPhone(updatedPhoneNumber);
                }
            }

            userPhoneRepo.saveAll(oldUserPhoneNumbers);
            userRepository.save(oldUser);
            doctorRepository.save(oldDoctor);
            return "Doctor updated  successfully";

        } else
            throw new IllegalArgumentException("Invalid id " + doctorId + ".....");
    }

    @Override
    public String deleteDoctorById(Long doctorId) {
        log.info("Admin want to delete doctor with id {}",doctorId);
        if (doctorId > 0) {
            Doctor doctor = doctorRepository.findById(doctorId)
                    .orElseThrow(() -> new RecordNotFoundException("No doctor with id " + doctorId));

            User unEnabledUser = doctor.getUser();
            if (unEnabledUser.isEnabled()) {
                unEnabledUser.setUpdatedAt(LocalDateTime.now());
                unEnabledUser.setEnabled(false);
                userRepository.save(unEnabledUser);
                log.info("doctor with id {} disabled",doctorId);
                return "Doctor deleted successfully";
            }
            log.error("Admin tried to disable user that is already disabled");
            throw new UserDisabledException("This user is unEnabled already");
        }
        throw new IllegalArgumentException("Invalid id " + doctorId + ".....");
    }
}
