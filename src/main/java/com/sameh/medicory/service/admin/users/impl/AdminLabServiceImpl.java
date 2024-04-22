package com.sameh.medicory.service.admin.users.impl;

import com.sameh.medicory.entity.usersEntities.Lab;
import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.exception.ConflictException;
import com.sameh.medicory.exception.RecordNotFoundException;
import com.sameh.medicory.mapper.LabMapper;
import com.sameh.medicory.model.users.LabDTO;
import com.sameh.medicory.model.users.UserDTO;
import com.sameh.medicory.repository.LabRepository;
import com.sameh.medicory.repository.UserRepository;
import com.sameh.medicory.service.admin.users.AdminLabService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminLabServiceImpl implements AdminLabService {
    private final LabRepository labRepo;
    private final UserRepository userRepo;
    private final LabMapper labMap;

//TODO add logs

    @Override
    public List<LabDTO> showAllLabs() {
       List<Lab> labs = labRepo.findAll();
       if(!labs.isEmpty()){
           return labMap.toDTOs(labs);
       }
       throw new RecordNotFoundException("No labs");
    }
    @Override
    public LabDTO findLabById(Long labId) {
        if(labId>0) {
            Optional<Lab> labOptional= labRepo.findById(labId);
            if(labOptional.isPresent()){
                Lab lab = labOptional.get();
                return labMap.toDTO(lab);
            }
            throw new RecordNotFoundException("There is no Lab With this ID "+labId);
        }
        throw new RuntimeException("Invalid id"+labId);
    }

    @Override
    public LabDTO findLabByEmail(String userEmail) {
       Lab lab = labRepo.findByUserEmail(userEmail);
       if (lab !=null){
           return labMap.toDTO(lab);
       }
       throw new RecordNotFoundException("No Lab with this email "+ userEmail);
    }

    @Override
    public List<LabDTO> findLabByName(String labName) {
        List<Lab> labs = labRepo.findByName(labName);
        if(! labs.isEmpty()){
            return labMap.toDTOs(labs);
        }
        throw new RecordNotFoundException("No labs with name : " +labName);
    }
    @Override
public String addLab(LabDTO newLab) {
    Lab lab = labMap.toEntity(newLab);
    User user = lab.getUser();
    User existingUser = userRepo.findByEmail(user.getEmail());
    if (existingUser == null) {
        LocalDateTime currentDateTime = LocalDateTime.now();


        LocalDateTime localDateTime=LocalDateTime.now();
        user.setCreatedAt(localDateTime);
        user.setUpdatedAt(localDateTime);
        userRepo.save(user);
        labRepo.save(lab);
        return "Lab inserted successfully";
    }
    throw new ConflictException("The user email " + user.getEmail() + " already exists");
}
    @Override
    public String updateLab(LabDTO updatedLab, Long labId) {
        log.info("Updating lab with id: {}", labId);
        if (labId > 0) {
            Optional<Lab> labOptional = labRepo.findById(labId);
            if (labOptional.isPresent()) {
                Lab lab = labOptional.get();
                lab.setName(updatedLab.getName());
                lab.setGoogleMapsLink(updatedLab.getGoogleMapsLink());
                lab.setAddress(updatedLab.getAddress());
                lab.setOwnerName(updatedLab.getOwnerName());


                UserDTO updatedUserDTO = updatedLab.getUser();
                System.out.println(updatedUserDTO);
                if (updatedUserDTO != null) {
                    User user = lab.getUser();
                    user.setEmail(updatedUserDTO.getEmail());
                    user.setEnabled(updatedUserDTO.isEnabled());
                    user.setPassword(updatedUserDTO.getPassword());
                    user.setRole(updatedUserDTO.getRole());
                    user.setUpdatedAt(LocalDateTime.now());
                    lab.setUser(user);
                    userRepo.save(user);
                }

                labRepo.save(lab);
                log.info("Lab with id {} updated successfully", labId);
                return "Lab updated successfully";
            } else {
                log.warn("No lab found with id: {}", labId);
                throw new RecordNotFoundException("No lab with id: " + labId);
            }
        }
        log.error("Invalid lab id: {}", labId);
        throw new IllegalArgumentException("Invalid id: " + labId);
    }


    @Override
    public String deleteLab(Long labId) {
        if (labId > 0) {
            Optional<Lab> lab = labRepo.findById(labId);
            if (lab.isPresent()) {
                Lab l= lab.get();
                labRepo.deleteById(labId);
                userRepo.deleteById(l.getUser().getId());

                return "Deleted successfully";
            } else {
                throw new RecordNotFoundException("No lab with id: " + labId);
            }
        }
        throw new IllegalArgumentException("Invalid id: " + labId);
    }
}
