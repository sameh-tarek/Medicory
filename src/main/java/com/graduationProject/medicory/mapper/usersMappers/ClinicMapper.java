package com.graduationProject.medicory.mapper.usersMappers;

import com.graduationProject.medicory.entity.usersEntities.Clinic;
import com.graduationProject.medicory.model.users.clinic.ClinicDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicRequestDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicResponseDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicSearchResponseDTO;


public interface ClinicMapper {
    Clinic toRequestEntity(ClinicRequestDTO clinic);
    ClinicDTO toDto(Clinic clinic);
    ClinicResponseDTO toResponseDTO(Clinic clinic);
    ClinicSearchResponseDTO toSearchResponseDTO(Clinic clinic);
}
