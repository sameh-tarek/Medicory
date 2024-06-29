package com.graduationProject.medicory.mapper.usersMappers;

import com.graduationProject.medicory.entity.usersEntities.Lab;
import com.graduationProject.medicory.model.users.lab.LabDTO;
import com.graduationProject.medicory.model.users.lab.LabRequestDTO;
import com.graduationProject.medicory.model.users.lab.LabResponseDTO;
import com.graduationProject.medicory.model.users.lab.LabSearchResponseDTO;

public interface LabMapper {
    Lab toRequestEntity(LabRequestDTO lab);
    LabDTO toDTO(Lab lab);
    LabResponseDTO toResponseDTO(Lab lab);

    LabSearchResponseDTO toSearchResponseDTO(Lab lab);
}
