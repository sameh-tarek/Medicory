package com.sameh.medicory.service.admin.users.impl;

import com.sameh.medicory.entity.usersEntities.Clinic;
import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.exception.ConflictException;
import com.sameh.medicory.exception.RecordNotFoundException;
import com.sameh.medicory.exception.UserDisabledException;
import com.sameh.medicory.mapper.ClinicMapper;
import com.sameh.medicory.model.users.clinic.ClinicRequestDTO;
import com.sameh.medicory.model.users.UserDTO;
import com.sameh.medicory.model.users.clinic.ClinicResponseDTO;
import com.sameh.medicory.repository.ClinicRepository;
import com.sameh.medicory.repository.UserRepository;
import com.sameh.medicory.service.admin.users.AdminClinicService;
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
   private final  UserRepository userRepository;
   private final ClinicMapper map;


    @Override
    public ClinicResponseDTO findClinicByUserCode(String userCode) {
        Clinic clinic= clinicRepository.findByUserCode(userCode)
                .orElseThrow(()-> new RecordNotFoundException("No user *CLINIC* has code : " +userCode));
        return map.toResponseDTO(clinic);
    }

    @Override
    public List<ClinicResponseDTO> findClinicsByName(String name) {

       List<Clinic> clinics= clinicRepository.findByName(name);
       if(!clinics.isEmpty()) {
         return clinics.stream()
                 .map(map::toResponseDTO)
                 .collect(Collectors.toList());
       }else {
           throw  new RecordNotFoundException("No clinics with this name : "+name);
       }
    }

    @Override
    public ClinicResponseDTO findClinicByUserEmail(String userEmail) {
        Clinic clinic =clinicRepository
                .findByUserEmail(userEmail)
                .orElseThrow(()-> new RecordNotFoundException("No user *CLINIC* with email "+userEmail));
                 return map.toResponseDTO(clinic);
        }

    @Override
    public ClinicRequestDTO showAllDataOfClinicByClinicId(long clinicId) {
        if(clinicId>0){
            Clinic clinic = clinicRepository.findById(clinicId)
                    .orElseThrow(() ->new RecordNotFoundException("No clinic with id "+clinicId));
            return map.toDto(clinic);
        }
        throw  new IllegalArgumentException("Invalid id "+clinicId);
    }


    @Override
    public String addNewClinic(ClinicRequestDTO clinicRequestDTO) {
        Clinic newClinic = map.toEntity(clinicRequestDTO);
        User user = newClinic.getUser();
        Optional< User> exsistingUser =userRepository.findByEmail(user.getEmail());
        if(!exsistingUser.isPresent()){

            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            userRepository.save(user);
            clinicRepository.save(newClinic);
            return "Clinic added successfully";
        }
        throw new ConflictException("The user email "+user.getEmail()+" already exist");

    }

    @Override
    public String updateClinic(ClinicRequestDTO updatedClinic, Long clinicId) {
      if(clinicId>0){
          Clinic clinic = clinicRepository.findById(clinicId)
                  .orElseThrow(()-> new RecordNotFoundException("No Clinic with id "+clinicId));

              clinic.setName(updatedClinic.getName());
              clinic.setGoogleMapsLink(updatedClinic.getGoogleMapsLink());
              clinic.setOwnerName(updatedClinic.getOwnerName());
              clinic.setSpecialization(updatedClinic.getSpecialization());

              UserDTO updatedUser=updatedClinic.getUser();
              User user =clinic.getUser();
              if(updatedUser != null){
                  user.setEmail(updatedUser.getEmail());
                  user.setPassword(updatedUser.getPassword());
                  user.setRole(updatedUser.getRole());
                  user.setEnabled(updatedUser.isEnabled());
                  user.setUpdatedAt(LocalDateTime.now());
              }
              userRepository.save(user);
              clinicRepository.save(clinic);
              return "Clinic updted sucessfully";
          }

    throw new IllegalArgumentException("Invalid id "+clinicId);
    }

    @Override
    public String deleteClinicById(Long clinicId) {
        if(clinicId>0) {
            Clinic clinic = clinicRepository.findById(clinicId)
                    .orElseThrow(()-> new RecordNotFoundException("No Clinic with id "+clinicId));

           User unEnabledUser= clinic.getUser();
        if(unEnabledUser.isEnabled()) {
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
