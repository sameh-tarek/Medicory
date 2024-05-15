package com.graduationProject.medicory.mapper.impl;

import com.graduationProject.medicory.entity.usersEntities.Lab;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.mapper.usersMappers.LabMapper;
import com.graduationProject.medicory.mapper.phonesMappers.UserPhoneNumberMapper;
import com.graduationProject.medicory.model.users.lab.LabDTO;
import com.graduationProject.medicory.model.users.lab.LabRequestDTO;
import com.graduationProject.medicory.model.users.lab.LabResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LabMappperImpl implements LabMapper {
    private final UserPhoneNumberMapper phoneNumberMapper;


    @Override
    public Lab toRequestEntity(LabRequestDTO lab) {
        User user = User.builder()
                .role(lab.getRole())
                .email(lab.getEmail())
                .enabled(lab.isEnabled())
                .userPhoneNumbers(
                        phoneNumberMapper.toRequestEntity(lab.getUserPhoneNumbers())
                )
                .build();

        return Lab.builder()
                .name(lab.getName())
                .googleMapsLink(lab.getGoogleMapsLink())
                .address(lab.getAddress())
                .ownerName(lab.getOwnerName())
                .user(user)
                .build();
    }

    @Override
    public LabDTO toDTO(Lab lab) {
        User user = lab.getUser();
        return LabDTO.builder()
                .id(lab.getId())
                .name(lab.getName())
                .googleMapsLink(lab.getGoogleMapsLink())
                .address(lab.getAddress())
                .ownerName(lab.getOwnerName())
                .code(user.getCode())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .isEnabled(user.isEnabled())
                .updatedAt(user.getUpdatedAt())
                .createdAt(user.getCreatedAt())
                .userPhoneNumbers(phoneNumberMapper.toRequestDTO(
                        user.getUserPhoneNumbers()
                ))
                .build();
    }


    @Override
    public LabResponseDTO toResponseDTO(Lab lab) {
        return new LabResponseDTO(
                lab.getId(),
                lab.getName(),
                lab.getUser().isEnabled()
        );
    }
}
