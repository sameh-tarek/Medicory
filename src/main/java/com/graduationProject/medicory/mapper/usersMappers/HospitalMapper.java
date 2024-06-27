package com.graduationProject.medicory.mapper.usersMappers;

import com.graduationProject.medicory.entity.usersEntities.Hospital;
import com.graduationProject.medicory.model.users.hospital.HospitalDTO;
import com.graduationProject.medicory.model.users.hospital.HospitalRequestDTO;
import com.graduationProject.medicory.model.users.hospital.HospitalResponseDTO;
import com.graduationProject.medicory.model.users.hospital.HospitalSearchResponseDTO;

public interface HospitalMapper {

    HospitalDTO toDTO(Hospital hospital);

    HospitalResponseDTO toResponseDTO(Hospital hospital);
    Hospital toRequestEntity(HospitalRequestDTO hospital);
    HospitalSearchResponseDTO toSearchResponseDTO(Hospital hospital);
}
