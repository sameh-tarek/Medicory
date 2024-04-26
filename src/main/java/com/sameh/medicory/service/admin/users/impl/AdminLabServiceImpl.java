package com.sameh.medicory.service.admin.users.impl;

import com.sameh.medicory.entity.usersEntities.Lab;
import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.exception.ConflictException;
import com.sameh.medicory.exception.RecordNotFoundException;
import com.sameh.medicory.exception.UserDisabledException;
import com.sameh.medicory.mapper.LabMapper;
import com.sameh.medicory.model.users.lab.LabRequestDTO;
import com.sameh.medicory.model.users.UserDTO;
import com.sameh.medicory.model.users.lab.LabResponseDTO;
import com.sameh.medicory.repository.LabRepository;
import com.sameh.medicory.repository.UserRepository;
import com.sameh.medicory.service.admin.users.AdminLabService;
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
public class AdminLabServiceImpl implements AdminLabService {
    private final LabRepository labRepo;
    private final UserRepository userRepo;
    private final LabMapper labMap;

//TODO add logs


    @Override
    public LabResponseDTO showAllLabDataById(Long labId) {
        if(labId>0) {
           Lab lab = labRepo.findById(labId)
                   .orElseThrow(()-> new RecordNotFoundException("No lab with id "+labId));
           return labMap.toResponseDTO(lab);
            }

        throw new RuntimeException("Invalid id"+labId);
    }

    @Override
    public LabResponseDTO findLabByEmail(String userEmail) {
        Lab lab = labRepo.findByUserEmail(userEmail)
                .orElseThrow(()-> new RecordNotFoundException("No user *LAB* with email "+userEmail));
        return labMap.toResponseDTO(lab);
    }

    @Override
    public LabResponseDTO findLabByUserCode(String userCode) {
        Lab lab = labRepo.findByUserCode(userCode)
                .orElseThrow(()-> new RecordNotFoundException("No lab with code "+userCode));
        return labMap.toResponseDTO(lab);
    }

    @Override
    public List<LabResponseDTO> findLabByName(String labName) {
        List<Lab> labs = labRepo.findByName(labName);
        if(!labs.isEmpty()){
            return labs.stream()
                    .map(labMap :: toResponseDTO)
                    .collect(Collectors.toList());
        }throw new RecordNotFoundException("No labs with name "+labName);
    }
    @Override
    public String addLab(LabRequestDTO newLab) {
       Lab lab = labMap.toEntity(newLab);
        User user = lab.getUser();
        Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
       if ( !existingUser.isPresent()) {

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepo.save(user);
        labRepo.save(lab);
        return "Lab inserted successfully";
     }
    throw new ConflictException("The user email " + user.getEmail() + " already exists");
}
    @Override
    public String updateLab(LabRequestDTO updatedLab, Long labId) {
        log.info("Updating lab with id: {}", labId);
        if (labId > 0) {
          Lab oldLab = labRepo.findById(labId)
                  .orElseThrow(()-> new RecordNotFoundException("No lab with id "+labId));
                oldLab.setName(updatedLab.getName());
                oldLab.setGoogleMapsLink(updatedLab.getGoogleMapsLink());
                oldLab.setAddress(updatedLab.getAddress());
                oldLab.setOwnerName(updatedLab.getOwnerName());

                UserDTO updatedUserDTO = updatedLab.getUser();

                  User user = oldLab.getUser();
                if (updatedUserDTO != null) {
                    user.setEmail(updatedUserDTO.getEmail());
                    user.setEnabled(updatedUserDTO.isEnabled());
                    user.setPassword(updatedUserDTO.getPassword());
                    user.setRole(updatedUserDTO.getRole());
                    user.setUpdatedAt(LocalDateTime.now());
                    oldLab.setUser(user);
                }
                 userRepo.save(user);
                labRepo.save(oldLab);
                log.info("Lab with id {} updated successfully", labId);
                return "Lab updated successfully";
            }
        log.error("Invalid lab id: {}", labId);
        throw new IllegalArgumentException("Invalid id: " + labId);
    }


    @Override
    public String deleteLab(Long labId) {
        if (labId > 0) {
            Lab lab = labRepo.findById(labId)
                    .orElseThrow(()-> new RecordNotFoundException("No lab with id "+labId));

         User unEnabledUser= lab.getUser();
         if(unEnabledUser.isEnabled()){
         unEnabledUser.setEnabled(false);
         unEnabledUser.setUpdatedAt(LocalDateTime.now());
         
         userRepo.save(unEnabledUser);
         return "Deleted successfully";
        }
            throw new UserDisabledException("This user is unEnabled already");
        }
        throw new IllegalArgumentException("Invalid id: " + labId);
    }
}
