package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.usersEntities.Lab;
import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.model.users.LabDTO;
import com.sameh.medicory.model.users.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LabMapper {
    Lab toEntity(LabDTO labDTO);
    LabDTO toDTO(Lab lab);
    List<LabDTO> toDTOs(List<Lab> labs);
    UserDTO toUserDTO(User user);
    List<Lab> toEntity(List<LabDTO> labs);
}
