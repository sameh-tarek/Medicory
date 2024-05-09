package com.graduationProject.medicory.mapper.impl;

import com.graduationProject.medicory.entity.usersEntities.Clinic;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.mapper.ClinicMapper;
import com.graduationProject.medicory.mapper.UserMapper;
import com.graduationProject.medicory.mapper.UserPhoneNumberMapper;
import com.graduationProject.medicory.model.users.clinic.ClinicRequestDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClinicMapperImpl implements ClinicMapper {

    private final UserMapper map;
    private final UserPhoneNumberMapper phoneNumberMapper;

    @Override
    public Clinic toEntity(ClinicDTO clinicDTO) {
        return new Clinic(
                clinicDTO.getId(),
                clinicDTO.getName(),
                clinicDTO.getGoogleMapsLink(),
                clinicDTO.getAddress(),
                clinicDTO.getOwnerName(),
                clinicDTO.getSpecialization()
                , map.toEntity(clinicDTO.getUser())
        );
    }

    @Override
    public ClinicDTO toDto(Clinic clinic) {
        return new ClinicDTO(
                clinic.getId(),
                clinic.getName(),
                clinic.getGoogleMapsLink(),
                clinic.getAddress(),
                clinic.getOwnerName(),
                clinic.getSpecialization(),
                map.toDto(clinic.getUser())
        );
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
        User userData = User.builder()
                .role(clinic.getRole())
                .enabled(clinic.isEnabled())
                .email(clinic.getEmail())
                .userPhoneNumbers(
                        phoneNumberMapper.toRequestEntity(clinic.getUserPhoneNumbers())
                )
                .build();
        return Clinic.builder()
                .name(clinic.getName())
                .address(clinic.getAddress())
                .googleMapsLink(clinic.getGoogleMapsLink())
                .ownerName(clinic.getOwnerName())
                .specialization(clinic.getSpecialization())
                .user(userData)
                .build();
    }


}
