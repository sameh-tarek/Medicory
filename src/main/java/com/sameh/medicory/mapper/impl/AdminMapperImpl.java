package com.sameh.medicory.mapper.impl;

import com.sameh.medicory.entity.usersEntities.Admin;
import com.sameh.medicory.mapper.AdminMapper;
import com.sameh.medicory.mapper.UserMapper;
import com.sameh.medicory.model.users.admin.AdminRequestDTO;
import com.sameh.medicory.model.users.admin.AdminResponseDTO;
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
                +" "
                +admin.getLastName()
        );

    }
}
