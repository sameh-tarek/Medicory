package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.usersEntities.Admin;
import com.sameh.medicory.model.users.admin.AdminRequestDTO;
import com.sameh.medicory.model.users.admin.AdminResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

public interface AdminMapper {
    Admin toEntity(AdminRequestDTO adminRequestDTO);
    AdminRequestDTO toDTO(Admin admin );
    AdminResponseDTO toResponseDTO(Admin admin);

}
