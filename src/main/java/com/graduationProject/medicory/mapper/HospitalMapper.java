package com.graduationProject.medicory.mapper;

import com.graduationProject.medicory.entity.usersEntities.Hospital;
import com.graduationProject.medicory.model.users.hospital.HospitalRequestDTO;
import com.graduationProject.medicory.model.users.hospital.HospitalDTO;
import com.graduationProject.medicory.model.users.hospital.HospitalResponseDTO;

public interface HospitalMapper {

    HospitalDTO toDTO(Hospital hospital);

    HospitalResponseDTO toResponseDTO(Hospital hospital);

    Hospital toEntity(HospitalDTO hospitalDTO);
    Hospital toRequestEntity(HospitalRequestDTO hospital);
}
