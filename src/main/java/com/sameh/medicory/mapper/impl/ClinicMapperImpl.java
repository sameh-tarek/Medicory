package com.sameh.medicory.mapper.impl;

import com.sameh.medicory.entity.usersEntities.Clinic;
import com.sameh.medicory.mapper.ClinicMapper;
import com.sameh.medicory.mapper.UserMapper;
import com.sameh.medicory.model.users.clinic.ClinicRequestDTO;
import com.sameh.medicory.model.users.clinic.ClinicResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClinicMapperImpl implements ClinicMapper {

    private final UserMapper map;

    @Override
    public Clinic toEntity(ClinicRequestDTO clinicRequestDTO) {
        return new Clinic(
                clinicRequestDTO.getId(),
                clinicRequestDTO.getName(),
                clinicRequestDTO.getGoogleMapsLink(),
                clinicRequestDTO.getAddress(),
                clinicRequestDTO.getOwnerName(),
                clinicRequestDTO.getSpecialization()
                , map.toEntity(clinicRequestDTO.getUser())
        );
    }

    @Override
    public ClinicRequestDTO toDto(Clinic clinic) {
        return new ClinicRequestDTO(
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


}
