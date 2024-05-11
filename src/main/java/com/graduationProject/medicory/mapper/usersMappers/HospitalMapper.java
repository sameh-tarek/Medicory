package com.graduationProject.medicory.mapper.usersMappers;

import com.graduationProject.medicory.entity.usersEntities.Hospital;
import com.graduationProject.medicory.model.users.hospital.HospitalDTO;
import com.graduationProject.medicory.model.users.hospital.HospitalRequestDTO;
import com.graduationProject.medicory.model.users.hospital.HospitalResponseDTO;

public interface HospitalMapper {

    HospitalDTO toDTO(Hospital hospital);

    HospitalResponseDTO toResponseDTO(Hospital hospital);
    Hospital toRequestEntity(HospitalRequestDTO hospital);
}
