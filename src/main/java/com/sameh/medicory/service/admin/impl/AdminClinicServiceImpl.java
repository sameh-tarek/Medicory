package com.sameh.medicory.service.admin.impl;

import com.sameh.medicory.entity.usersEntities.Clinic;
import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.exception.ConflictException;
import com.sameh.medicory.exception.RecordNotFoundException;
import com.sameh.medicory.mapper.ClinicMapper;
import com.sameh.medicory.model.users.ClinicDTO;
import com.sameh.medicory.repository.ClinicRepository;
import com.sameh.medicory.repository.UserRepository;
import com.sameh.medicory.service.admin.AdminClinicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor

//TODO enhance this
public class AdminClinicServiceImpl implements AdminClinicService {
    @Autowired
    ClinicRepository clinicRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ClinicMapper map;
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
        }else throw new RecordNotFoundException("Invalid id : ");
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


    // TODO *edit=>ex

    //TODO if adding to an existing user_id
    @Override
    public String addNewClinic(ClinicDTO clinicDTO) {
        Clinic newClinic = map.toEntity(clinicDTO);
        if (clinicRepository.findByUserEmail(newClinic.getUser().getEmail()) != null){
            //save user first
            if (newClinic.getUser() != null) {
                User newUser = userRepository.save(newClinic.getUser());
                newClinic.setUser(newUser);
            }
        clinicRepository.save(newClinic);
        return "Clinic added successfully";
        }
        else throw new ConflictException("This email already exist");

    }
      //TODO update
    @Override
    public String updateClinic(ClinicDTO clinicDTO, Long clinicId) {
        return null;
    }

    @Override
    public String deleteClinicById(Long clinicId) {
        if (clinicRepository.existsById(clinicId)) {
            clinicRepository.deleteById(clinicId);
            return "Clinic deleted successfully";
        }else
            throw new RecordNotFoundException("Clinic not found with ID: " + clinicId);


    }
}
