package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.usersEntities.Admin;
import com.sameh.medicory.model.users.AdminDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    Admin toEntity(AdminDTO adminDTO);
    AdminDTO toDTO(Admin admin );
    List<Admin> toEntities(List<AdminDTO> adminDTOs);
    List<AdminDTO> toDTOs(List<Admin> admins);
}
