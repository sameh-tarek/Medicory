package com.graduationProject.medicory.service.admin.users;

import com.graduationProject.medicory.model.users.lab.LabRequestDTO;
import com.graduationProject.medicory.model.users.lab.LabResponseDTO;

import java.util.List;

public interface AdminLabService {

    LabRequestDTO showAllLabDataById(Long labId);
    LabResponseDTO findLabByEmail(String userEmail);
   LabResponseDTO findLabByUserCode(String userCode);
    List<LabResponseDTO>findLabByName(String labName);
    String addLab(LabRequestDTO newLab);
    String updateLab(LabRequestDTO updatedLab, Long labId);
    String deleteLab(Long labId);


}
