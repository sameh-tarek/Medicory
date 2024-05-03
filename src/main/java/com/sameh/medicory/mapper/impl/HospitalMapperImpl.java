package com.sameh.medicory.mapper.impl;

import com.sameh.medicory.entity.usersEntities.Hospital;
import com.sameh.medicory.mapper.HospitalMapper;
import com.sameh.medicory.mapper.UserMapper;
import com.sameh.medicory.model.users.hospital.HospitalDTO;
import com.sameh.medicory.model.users.hospital.HospitalResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HospitalMapperImpl implements HospitalMapper {

    private final UserMapper map;

    @Override
    public HospitalDTO toDTO(Hospital hospital) {
        return new HospitalDTO(
                hospital.getId(),
                hospital.getName(),
                hospital.getGoogleMapsLink(),
                hospital.getAddress(),
                map.toDto(
                        hospital.getUser()
                )
        );
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
    public Hospital toEntity(HospitalDTO hospitalDTO) {
        return new Hospital(
                hospitalDTO.getId(),
                hospitalDTO.getName(),
                hospitalDTO.getGoogleMapsLink(),
                hospitalDTO.getAddress(),
                map.toEntity(
                        hospitalDTO.getUser()
                )
        );
    }
}
