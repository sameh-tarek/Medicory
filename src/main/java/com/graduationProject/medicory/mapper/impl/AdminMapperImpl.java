package com.graduationProject.medicory.mapper.impl;

import com.graduationProject.medicory.entity.usersEntities.Admin;
import com.graduationProject.medicory.mapper.AdminMapper;
import com.graduationProject.medicory.mapper.UserMapper;
import com.graduationProject.medicory.model.users.admin.AdminRequestDTO;
import com.graduationProject.medicory.model.users.admin.AdminResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminMapperImpl implements AdminMapper {

    private final UserMapper map;

    @Override
    public Admin toEntity(AdminRequestDTO admin) {
        return new Admin(
                admin.getId(),
                admin.getFirstName(),
                admin.getLastName(),
                admin.getMaritalStatus(),
                admin.getGender(),
                map.toEntity(
                        admin.getUser()
                )
        );
    }

    @Override
    public AdminRequestDTO toDTO(Admin admin) {
        return new AdminRequestDTO(
                admin.getId(),
                admin.getFirstName(),
                admin.getLastName(),
                admin.getMaritalStatus(),
                admin.getGender(),
                map.toDto(
                        admin.getUser()
                )
        );
    }

    @Override
    public AdminResponseDTO toResponseDTO(Admin admin) {
        return new AdminResponseDTO(
                admin.getId(),
                admin.getFirstName()
                        + " "
                        + admin.getLastName(),
                admin.getUser().isEnabled()
        );

    }
}
