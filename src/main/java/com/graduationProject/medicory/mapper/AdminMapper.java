package com.graduationProject.medicory.mapper;

import com.graduationProject.medicory.entity.usersEntities.Admin;
import com.graduationProject.medicory.model.users.admin.AdminRequestDTO;
import com.graduationProject.medicory.model.users.admin.AdminResponseDTO;

public interface AdminMapper {
    Admin toEntity(AdminRequestDTO adminRequestDTO);
    AdminRequestDTO toDTO(Admin admin );
    AdminResponseDTO toResponseDTO(Admin admin);

}
