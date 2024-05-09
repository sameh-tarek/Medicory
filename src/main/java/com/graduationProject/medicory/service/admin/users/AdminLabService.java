package com.graduationProject.medicory.service.admin.users;

import com.graduationProject.medicory.model.users.lab.LabRequestDTO;
import com.graduationProject.medicory.model.users.lab.LabDTO;
import com.graduationProject.medicory.model.users.lab.LabResponseDTO;

import java.util.List;

public interface AdminLabService {

    LabDTO showAllLabDataById(Long labId);
    LabResponseDTO findLabByEmail(String userEmail);
   LabResponseDTO findLabByUserCode(String userCode);
    List<LabResponseDTO>findLabByName(String labName);
    String addLab(LabRequestDTO newLab);
    String updateLab(LabDTO updatedLab, Long labId);
    String deleteLab(Long labId);


}
