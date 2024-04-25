package com.sameh.medicory.service.admin.users.impl;

import com.sameh.medicory.entity.usersEntities.Doctor;
import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.exception.ConflictException;
import com.sameh.medicory.exception.RecordNotFoundException;
import com.sameh.medicory.mapper.DoctorMapper;
import com.sameh.medicory.mapper.UserMapper;
import com.sameh.medicory.model.users.DoctorDTO;
import com.sameh.medicory.repository.DoctorRepository;
import com.sameh.medicory.repository.UserRepository;
import com.sameh.medicory.service.admin.users.AdminDoctorService;
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


    @Override
    public List<DoctorDTO> findAllDoctors() {
        List<Doctor> allDoctors =doctorRepository.findAll();
        if(!allDoctors.isEmpty()){
           return allDoctors.stream()
                    .map(doctorMapper::toDTO)
                    .collect(Collectors.toList());
        }else
           throw new RecordNotFoundException("NO doctors found");

        }


    @Override
    public DoctorDTO findDoctorById(Long doctorId) {
       if(doctorId>0){
           Optional<Doctor> optionalDoctor =  doctorRepository.findById(doctorId);
           if(optionalDoctor.isPresent()){
               Doctor doctor =optionalDoctor.get();
               return doctorMapper.toDTO(doctor);
           }
           else
               throw new RecordNotFoundException("No doctor with id "+doctorId);
       }else
           throw new IllegalArgumentException("Invalid id "+doctorId +".....");
    }

    @Override
    public DoctorDTO findDoctoByUserEmail(String userEmail) {
        Doctor doctor = doctorRepository.findDoctorByUserEmail(userEmail);
         if(doctor !=null){
             return doctorMapper.toDTO(doctor);
         }
         else
             throw  new RecordNotFoundException("No user *DOCTOR* with this email "+ userEmail);
    }
    //TODO find by name handle all cases :)
    @Override
    public List<DoctorDTO> findDoctorbyFullName(String fullName) {
        return null;
    }

    @Override
    public String addNewDoctor(DoctorDTO newDoctorDTO) {
        Doctor newDoctor =doctorMapper.toEntity(newDoctorDTO);
        User newUser = newDoctor.getUser();
       Optional< User> existing = userRepository.findByEmail(newUser.getEmail());
        if(existing.isPresent()){
            newUser.setUpdatedAt(LocalDateTime.now());
            newUser.setCreatedAt(LocalDateTime.now());
            userRepository.save(newUser);
            doctorRepository.save(newDoctor);
            return  "Doctor added successfully";
        } else
            throw new ConflictException("User with this email "+ newUser.getEmail()+" already exist ");
    }
   //TODO enhance update function for all users
    @Override
    public String updateDoctor(DoctorDTO updatedDoctorDTO, Long doctorId) {
    if(doctorId>0){
    Optional<Doctor> optionalDoctor=doctorRepository.findById(doctorId);
    if(optionalDoctor.isPresent()){
        Doctor oldDoctor =  optionalDoctor.get();
        oldDoctor.setFirstName(updatedDoctorDTO.getFirstName());
        oldDoctor.setMiddleName(updatedDoctorDTO.getMiddleName());
        oldDoctor.setLastName(updatedDoctorDTO.getLastName());
        oldDoctor.setSpecialization(updatedDoctorDTO.getSpecialization());
        oldDoctor.setLicenceNumber(updatedDoctorDTO.getLicenceNumber());
        oldDoctor.setNationalId(updatedDoctorDTO.getNationalId());
        oldDoctor.setMaritalStatus(updatedDoctorDTO.getMaritalStatus());
        oldDoctor.setGender(updatedDoctorDTO.getGender());


        User updatedUser=  userMapper.toEntity(updatedDoctorDTO.getUser());
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

    }else
        throw new RecordNotFoundException("No doctor with id "+ doctorId);

    } else
        throw new IllegalArgumentException("Invalid id "+doctorId +".....");
    }

    @Override
    public String deleteDoctorById(Long doctorId) {
       if(doctorId>0){
           Optional<Doctor> optionalDoctor= doctorRepository.findById(doctorId);
           if(optionalDoctor.isPresent()) {
                Doctor doctor= optionalDoctor.get();
                doctorRepository.deleteById(doctorId);
                userRepository.deleteById(doctor.getUser().getId());
               return "Doctor deleted successfully";
           }else
               throw new RecordNotFoundException("No doctor with id "+doctorId);
       }else
           throw new IllegalArgumentException("Invalid id "+doctorId +".....");
    }
}
