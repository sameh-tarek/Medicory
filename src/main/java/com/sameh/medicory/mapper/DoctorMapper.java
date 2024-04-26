package com.sameh.medicory.mapper;


import com.sameh.medicory.entity.usersEntities.Doctor;
import com.sameh.medicory.model.users.doctor.DoctorRequestDTO;
import com.sameh.medicory.model.users.doctor.DoctorResponseDTO;


public interface DoctorMapper {
    Doctor toEntity(DoctorRequestDTO doctorRequestDTO);
    DoctorRequestDTO toDTO(Doctor doctor);
    DoctorResponseDTO toResponseDTO(Doctor doctor);
}
