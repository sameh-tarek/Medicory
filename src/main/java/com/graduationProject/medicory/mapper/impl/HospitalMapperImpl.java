package com.graduationProject.medicory.mapper.impl;

import com.graduationProject.medicory.entity.usersEntities.Hospital;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.mapper.HospitalMapper;
import com.graduationProject.medicory.mapper.UserPhoneNumberMapper;
import com.graduationProject.medicory.model.users.hospital.HospitalDTO;
import com.graduationProject.medicory.model.users.hospital.HospitalRequestDTO;
import com.graduationProject.medicory.model.users.hospital.HospitalResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HospitalMapperImpl implements HospitalMapper {

    private final UserPhoneNumberMapper phoneNumberMapper;

    @Override
    public HospitalDTO toDTO(Hospital hospital) {
        User user = hospital.getUser();

        return HospitalDTO.builder()
                .name(hospital.getName())
                .googleMapsLink(hospital.getGoogleMapsLink())
                .address(hospital.getAddress())
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
    public HospitalResponseDTO toResponseDTO(Hospital hospital) {
        return new HospitalResponseDTO(
                hospital.getId(),
                hospital.getName(),
                hospital.getUser().isEnabled()
        );
    }

    @Override
    public Hospital toRequestEntity(HospitalRequestDTO hospital) {

        return Hospital.builder()
                .name(hospital.getName())
                .googleMapsLink(hospital.getGoogleMapsLink())
                .address(hospital.getAddress())
                .user(toUserEntity(hospital))
                .build();
    }

    private User toUserEntity(HospitalRequestDTO hospital) {
        return User.builder()
                .role(hospital.getRole())
                .enabled(hospital.isEnabled())
                .email(hospital.getEmail())
                .userPhoneNumbers(
                        phoneNumberMapper.toRequestEntity(hospital.getUserPhoneNumbers())
                )
                .build();
    }
}
