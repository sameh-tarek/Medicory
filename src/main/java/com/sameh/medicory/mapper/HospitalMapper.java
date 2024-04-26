package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.usersEntities.Hospital;
import com.sameh.medicory.model.users.hospital.HospitalDTO;
import com.sameh.medicory.model.users.hospital.HospitalResponseDTO;

public interface HospitalMapper {

    HospitalDTO toDTO(Hospital hospital);

    HospitalResponseDTO toResponseDTO(Hospital hospital);

    Hospital toEntity(HospitalDTO hospitalDTO);
}
