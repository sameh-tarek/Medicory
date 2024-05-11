package com.graduationProject.medicory.mapper.impl;

import com.graduationProject.medicory.entity.usersEntities.Admin;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.mapper.usersMappers.AdminMapper;
import com.graduationProject.medicory.mapper.phonesMappers.UserPhoneNumberMapper;
import com.graduationProject.medicory.model.users.admin.AdminDTO;
import com.graduationProject.medicory.model.users.admin.AdminRequestDTO;
import com.graduationProject.medicory.model.users.admin.AdminResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminMapperImpl implements AdminMapper {

    private final UserPhoneNumberMapper phoneNumberMapper;

    @Override
    public Admin toEntity(AdminDTO admin) {

        return Admin.builder()
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .maritalStatus(admin.getMaritalStatus())
                .gender(admin.getGender())
                .user(toUserEntity(admin))
                .build();
    }
    @Override
    public AdminDTO toDTO(Admin admin) {
        User user = admin.getUser();
        return AdminDTO.builder()
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .maritalStatus(admin.getMaritalStatus())
                .gender(admin.getGender())
                .email(user.getEmail())
                .code(user.getCode())
                .password(user.getPassword())
                .role(user.getRole())
                .isEnabled(user.isEnabled())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .userPhoneNumbers(phoneNumberMapper.toRequestDTO(
                        user.getUserPhoneNumbers()
                ))
                .build();

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

    @Override
    public Admin toRequestEntity(AdminRequestDTO admin) {

        return Admin.builder()
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .maritalStatus(admin.getMaritalStatus())
                .gender(admin.getGender())
                .user(toUserRequestEntity(admin))
                .build();

    }

    private User toUserRequestEntity(AdminRequestDTO admin) {
        return User.builder()
                .role(admin.getRole())
                .enabled(admin.isEnabled())
                .email(admin.getEmail())
                .userPhoneNumbers(
                        phoneNumberMapper.toRequestEntity(admin.getPhoneNumbers())
                )
                .build();
    }

    private User toUserEntity(AdminDTO admin) {
        return User.builder()
                .code(admin.getCode())
                .role(admin.getRole())
                .enabled(admin.isEnabled())
                .email(admin.getEmail())
                .password(admin.getPassword())
                .createdAt(admin.getCreatedAt())
                .updatedAt(admin.getUpdatedAt())
                .userPhoneNumbers(
                        phoneNumberMapper.toRequestEntity(admin.getUserPhoneNumbers())
                )
                .build();
    }

}
