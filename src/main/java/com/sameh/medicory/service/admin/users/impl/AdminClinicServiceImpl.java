package com.sameh.medicory.service.admin.users.impl;

import com.sameh.medicory.entity.usersEntities.Clinic;
import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.exception.ConflictException;
import com.sameh.medicory.exception.RecordNotFoundException;
import com.sameh.medicory.mapper.ClinicMapper;
import com.sameh.medicory.model.users.ClinicDTO;
import com.sameh.medicory.model.users.UserDTO;
import com.sameh.medicory.repository.ClinicRepository;
import com.sameh.medicory.repository.UserRepository;
import com.sameh.medicory.service.admin.users.AdminClinicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor

//TODO enhance this
public class AdminClinicServiceImpl implements AdminClinicService {

   private final ClinicRepository clinicRepository;
   private final  UserRepository userRepository;
   private final ClinicMapper map;

    @Override
    public ClinicDTO findClinicById(Long clinicId) {
        if (clinicId > 0){
            Optional<Clinic> clinicOptional = clinicRepository.findById(clinicId);
            if (clinicOptional.isPresent()) {
                Clinic clinic = clinicOptional.get();
                return map.toDto(clinic);
            } else {
                throw new RecordNotFoundException("Clinic not found with ID: " + clinicId);
            }
        }else throw new RuntimeException("Invalid id : ");
    }


    @Override
    public List<ClinicDTO> findClinicsByName(String name) {

       List<Clinic> clinics= clinicRepository.findByName(name);
       if(!clinics.isEmpty()) {
           List<ClinicDTO> clinicDTOs = map.toDto(clinics);
           return clinicDTOs;
       }else {
           throw  new RecordNotFoundException("No clinics with this name : "+name);
       }
    }

    @Override
    public ClinicDTO findClinicByUserEmail(String userEmail) {
        Clinic clinic =clinicRepository.findByUserEmail(userEmail);
        if(clinic !=null){
            ClinicDTO clinicDTO =map.toDto(clinic);
            return clinicDTO;
        }else
            throw  new RecordNotFoundException("No User with this email :"+userEmail);
    }

    @Override
    public List<ClinicDTO> getAllClinics() {
       List<Clinic> clinics = clinicRepository.findAll();
       if(!clinics.isEmpty()){
           return map.toDto(clinics);
       }else
           throw new RecordNotFoundException("No clinics :)");
    }

    @Override
    public String addNewClinic(ClinicDTO clinicDTO) {
        Clinic newClinic = map.toEntity(clinicDTO);
        User user = newClinic.getUser();
       Optional< User> exsistingUser =userRepository.findByEmail(user.getEmail());
        if(exsistingUser.isPresent()){

            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
            clinicRepository.save(newClinic);
            return "Clinic added successfully";
        }
        throw new ConflictException("The user email "+user.getEmail()+" already exist");

    }
      //TODO update
    @Override
    public String updateClinic(ClinicDTO updatedClinic, Long clinicId) {
      if(clinicId>0){
          Optional<Clinic> clinicOptional = clinicRepository.findById(clinicId);
          if(clinicOptional.isPresent()){
              Clinic clinic= clinicOptional.get();
              clinic.setName(updatedClinic.getName());
              clinic.setGoogleMapsLink(updatedClinic.getGoogleMapsLink());
              clinic.setOwnerName(updatedClinic.getOwnerName());
              clinic.setSpecialization(updatedClinic.getSpecialization());


              //user
              UserDTO updatedUser=updatedClinic.getUser();
              if(updatedUser != null){
                  User user =clinic.getUser();
                  user.setEmail(updatedUser.getEmail());
                  user.setPassword(updatedUser.getPassword());
                  user.setRole(updatedUser.getRole());
                  user.setEnabled(updatedUser.isEnabled());
                  user.setUpdatedAt(LocalDateTime.now());

                  userRepository.save(user);

              }
              clinicRepository.save(clinic);
              return "Clinic updted sucessfully";
          }
          throw new RecordNotFoundException("No Clinic with id: " + clinicId);

    }
        throw new RecordNotFoundException("No lab with id: " + clinicId);
    }

    @Override
    public String deleteClinicById(Long clinicId) {
      Optional<Clinic> clinicOptional = clinicRepository.findById(clinicId);
      if(clinicOptional.isPresent()){
           Clinic clinic= clinicOptional.get();
           clinicRepository.deleteById(clinicId);
           userRepository.deleteById(clinic.getUser().getId());
            return "Clinic deleted successfully";
        }else
            throw new RecordNotFoundException("Clinic not found with ID: " + clinicId);


    }
}
