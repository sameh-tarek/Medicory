package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.usersEntities.Lab;
import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.model.users.lab.LabRequestDTO;
import com.sameh.medicory.model.users.UserDTO;
import com.sameh.medicory.model.users.lab.LabResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

public interface LabMapper {
    Lab toEntity(LabRequestDTO labRequestDTO);
    LabRequestDTO toDTO(Lab lab);
    LabResponseDTO toResponseDTO(Lab lab);
}
