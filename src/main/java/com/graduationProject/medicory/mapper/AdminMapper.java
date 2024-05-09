package com.graduationProject.medicory.mapper;

import com.graduationProject.medicory.entity.usersEntities.Admin;
import com.graduationProject.medicory.model.users.admin.AdminDTO;
import com.graduationProject.medicory.model.users.admin.AdminResponseDTO;

public interface AdminMapper {
    Admin toEntity(AdminDTO adminDTO);
    Admin toRequestEntity(AdminDTO admin);
    AdminDTO toDTO(Admin admin );
    AdminResponseDTO toResponseDTO(Admin admin);

}
