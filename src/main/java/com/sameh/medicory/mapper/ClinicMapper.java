package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.usersEntities.Clinic;
import com.sameh.medicory.model.users.clinic.ClinicRequestDTO;
import com.sameh.medicory.model.users.clinic.ClinicResponseDTO;
import com.sameh.medicory.model.users.owner.OwnerResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;


public interface ClinicMapper {
    Clinic toEntity(ClinicRequestDTO clinicRequestDTO);
    ClinicRequestDTO toDto(Clinic clinic);
    ClinicResponseDTO toResponseDTO(Clinic clinic);


}
