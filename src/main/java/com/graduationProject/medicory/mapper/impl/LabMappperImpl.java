package com.graduationProject.medicory.mapper.impl;

import com.graduationProject.medicory.entity.usersEntities.Lab;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.mapper.LabMapper;
import com.graduationProject.medicory.mapper.UserMapper;
import com.graduationProject.medicory.mapper.UserPhoneNumberMapper;
import com.graduationProject.medicory.model.users.lab.LabRequestDTO;
import com.graduationProject.medicory.model.users.lab.LabDTO;
import com.graduationProject.medicory.model.users.lab.LabResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LabMappperImpl implements LabMapper {

    private final UserMapper map;
    private final UserPhoneNumberMapper phoneNumberMapper;

    @Override
    public Lab toEntity(LabDTO labDTO) {
        return new Lab(
                labDTO.getId()
                , labDTO.getName()
                , labDTO.getGoogleMapsLink()
                , labDTO.getAddress()
                , labDTO.getOwnerName()
                , map.toEntity(
                labDTO.getUser()
        )
        );
    }

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
        return new LabDTO(
                lab.getId(),
                lab.getName(),
                lab.getGoogleMapsLink(),
                lab.getAddress(),
                lab.getOwnerName(),
                map.toDto(
                        lab.getUser()
                )
        );
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
