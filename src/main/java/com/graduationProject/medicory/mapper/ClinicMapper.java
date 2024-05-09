package com.graduationProject.medicory.mapper;

import com.graduationProject.medicory.entity.usersEntities.Clinic;
import com.graduationProject.medicory.model.users.clinic.ClinicDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicRequestDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicResponseDTO;


public interface ClinicMapper {
    Clinic toRequestEntity(ClinicRequestDTO clinic);
    ClinicDTO toDto(Clinic clinic);
    ClinicResponseDTO toResponseDTO(Clinic clinic);


}
