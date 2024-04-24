package com.sameh.medicory.service.admin.users;

import com.sameh.medicory.model.users.LabDTO;

import java.util.List;

public interface AdminLabService {
    List<LabDTO> showAllLabs();
    LabDTO findLabById(Long labId);
    LabDTO findLabByEmail(String userEmail);
    List<LabDTO>findLabByName(String labName);
    String addLab(LabDTO newLab);
    String updateLab(LabDTO updatedLab,Long labId);
    String deleteLab(Long labId);


}
