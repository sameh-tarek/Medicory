package com.graduationProject.medicory.mapper;

import com.graduationProject.medicory.entity.usersEntities.Lab;
import com.graduationProject.medicory.model.users.lab.LabDTO;
import com.graduationProject.medicory.model.users.lab.LabRequestDTO;
import com.graduationProject.medicory.model.users.lab.LabResponseDTO;

public interface LabMapper {
    Lab toRequestEntity(LabRequestDTO lab);
    LabDTO toDTO(Lab lab);
    LabResponseDTO toResponseDTO(Lab lab);
}
