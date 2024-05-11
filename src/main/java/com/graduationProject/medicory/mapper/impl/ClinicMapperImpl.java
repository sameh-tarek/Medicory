package com.graduationProject.medicory.mapper.impl;

import com.graduationProject.medicory.entity.usersEntities.Clinic;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.mapper.usersMappers.ClinicMapper;
import com.graduationProject.medicory.mapper.phonesMappers.UserPhoneNumberMapper;
import com.graduationProject.medicory.model.users.clinic.ClinicDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicRequestDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClinicMapperImpl implements ClinicMapper {

    private final UserPhoneNumberMapper phoneNumberMapper;


    @Override
    public ClinicDTO toDto(Clinic clinic) {
        User user = clinic.getUser();
        return ClinicDTO.builder()
                .id(clinic.getId())
                .name(clinic.getName())
                .googleMapsLink(clinic.getGoogleMapsLink())
                .address(clinic.getAddress())
                .ownerName(clinic.getOwnerName())
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
    public ClinicResponseDTO toResponseDTO(Clinic clinic) {
        return new ClinicResponseDTO(
                clinic.getId(),
                clinic.getName(),
                clinic.getUser().isEnabled()
        );
    }

    @Override
    public Clinic toRequestEntity(ClinicRequestDTO clinic) {

        return Clinic.builder()
                .name(clinic.getName())
                .address(clinic.getAddress())
                .googleMapsLink(clinic.getGoogleMapsLink())
                .ownerName(clinic.getOwnerName())
                .specialization(clinic.getSpecialization())
                .user(toUserEntity(clinic))
                .build();
    }

    private User toUserEntity(ClinicRequestDTO clinic) {
        return User.builder()
                .role(clinic.getRole())
                .enabled(clinic.isEnabled())
                .email(clinic.getEmail())
                .userPhoneNumbers(
                        phoneNumberMapper.toRequestEntity(clinic.getUserPhoneNumbers())
                )
                .build();
    }


}
