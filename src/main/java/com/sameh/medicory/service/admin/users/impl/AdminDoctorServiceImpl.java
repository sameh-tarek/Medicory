package com.sameh.medicory.service.admin.users.impl;

import com.sameh.medicory.entity.usersEntities.Doctor;
import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.exception.ConflictException;
import com.sameh.medicory.exception.RecordNotFoundException;
import com.sameh.medicory.exception.UserDisabledException;
import com.sameh.medicory.mapper.DoctorMapper;
import com.sameh.medicory.mapper.UserMapper;
import com.sameh.medicory.model.users.doctor.DoctorRequestDTO;
import com.sameh.medicory.model.users.doctor.DoctorResponseDTO;
import com.sameh.medicory.repository.DoctorRepository;
import com.sameh.medicory.repository.UserRepository;
import com.sameh.medicory.service.admin.users.AdminDoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor

public class AdminDoctorServiceImpl implements AdminDoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final DoctorMapper doctorMapper;
    private final UserMapper userMapper;

    @Override
    public DoctorRequestDTO showAllDoctorDataById(Long doctorId) {
       if(doctorId>0){
          Doctor doctor = doctorRepository.findById(doctorId)
                  .orElseThrow(()-> new RecordNotFoundException("No doctor  with id "+doctorId));
          return doctorMapper.toDTO(doctor);
       }
           throw new IllegalArgumentException("Invalid id "+doctorId +".....");
    }

    @Override
    public DoctorResponseDTO findDoctoByUserEmail(String userEmail) {
        Doctor doctor = doctorRepository
                .findDoctorByUserEmail(userEmail)
                .orElseThrow(()-> new RecordNotFoundException("No user *DOCTOR* with this email "+ userEmail));
              return doctorMapper.toResponseDTO(doctor);
    }

    @Override
    public DoctorResponseDTO findDoctorByUserCode(String userCode) {
        Doctor doctor = doctorRepository
                .findDoctorByUserCode(userCode)
                .orElseThrow(()-> new RecordNotFoundException("No user *DOCTOR* with code : "+userCode));
        return doctorMapper.toResponseDTO(doctor);
    }

    //TODO find by name handle all cases :)
    @Override
    public List<DoctorResponseDTO> findDoctorbyFullName(String fullName) {
        return  null;
    }
    @Override
    public String addNewDoctor(DoctorRequestDTO newDoctorRequestDTO) {
        Doctor newDoctor =doctorMapper.toEntity(newDoctorRequestDTO);
        User newUser = newDoctor.getUser();
       Optional< User> existing = userRepository.findByEmail(newUser.getEmail());
        if(!existing.isPresent()){
            newUser.setUpdatedAt(LocalDateTime.now());
            newUser.setCreatedAt(LocalDateTime.now());

            userRepository.save(newUser);
            doctorRepository.save(newDoctor);
            return  "Doctor added successfully";
        }
            throw new ConflictException("User with this email "+ newUser.getEmail()+" already exist ");
    }

   //TODO enhance update function for all users
    @Override
    public String updateDoctor(DoctorRequestDTO updatedDoctorRequestDTO, Long doctorId) {
    if(doctorId>0){
     Doctor oldDoctor =doctorRepository.findById(doctorId)
                     .orElseThrow(()-> new RecordNotFoundException("No doctor with id "+ doctorId));

        oldDoctor.setFirstName(updatedDoctorRequestDTO.getFirstName());
        oldDoctor.setMiddleName(updatedDoctorRequestDTO.getMiddleName());
        oldDoctor.setLastName(updatedDoctorRequestDTO.getLastName());
        oldDoctor.setSpecialization(updatedDoctorRequestDTO.getSpecialization());
        oldDoctor.setLicenceNumber(updatedDoctorRequestDTO.getLicenceNumber());
        oldDoctor.setNationalId(updatedDoctorRequestDTO.getNationalId());
        oldDoctor.setMaritalStatus(updatedDoctorRequestDTO.getMaritalStatus());
        oldDoctor.setGender(updatedDoctorRequestDTO.getGender());

        User updatedUser=  userMapper.toEntity(updatedDoctorRequestDTO.getUser());
        User oldUser = oldDoctor.getUser();

          if(updatedUser !=null){
            oldUser.setEmail(updatedUser.getEmail());
            oldUser.setPassword(updatedUser.getPassword());
            oldUser.setEnabled(updatedUser.isEnabled());
            oldUser.setRole(updatedUser.getRole());
            oldUser.setUpdatedAt(LocalDateTime.now());
          }

         userRepository.save(oldUser);
         doctorRepository.save(oldDoctor);
        return "Doctor updated  successfully";

    } else
        throw new IllegalArgumentException("Invalid id "+doctorId +".....");
    }

    @Override
    public String deleteDoctorById(Long doctorId) {
       if(doctorId>0){
           Doctor doctor= doctorRepository.findById(doctorId)
                   .orElseThrow(()-> new RecordNotFoundException("No doctor with id "+doctorId));

           User unEnabledUser = doctor.getUser();
           if(unEnabledUser.isEnabled()) {
               unEnabledUser.setUpdatedAt(LocalDateTime.now());
               unEnabledUser.setEnabled(false);
               userRepository.save(unEnabledUser);
               return "Doctor deleted successfully";
           }
           throw new UserDisabledException("This user is unEnabled already");
           }
           throw new IllegalArgumentException("Invalid id "+doctorId +".....");
    }
}
