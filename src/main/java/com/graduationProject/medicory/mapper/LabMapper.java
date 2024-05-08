package com.graduationProject.medicory.mapper;

import com.graduationProject.medicory.entity.usersEntities.Lab;
import com.graduationProject.medicory.model.users.lab.LabRequestDTO;
import com.graduationProject.medicory.model.users.lab.LabResponseDTO;

public interface LabMapper {
    Lab toEntity(LabRequestDTO labRequestDTO);
    LabRequestDTO toDTO(Lab lab);
    LabResponseDTO toResponseDTO(Lab lab);
}
