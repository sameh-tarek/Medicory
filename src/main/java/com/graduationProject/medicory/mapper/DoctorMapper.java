package com.graduationProject.medicory.mapper;


import com.graduationProject.medicory.entity.usersEntities.Doctor;
import com.graduationProject.medicory.model.users.doctor.DoctorRequestDTO;
import com.graduationProject.medicory.model.users.doctor.DoctorResponseDTO;


public interface DoctorMapper {
    Doctor toEntity(DoctorRequestDTO doctorRequestDTO);
    DoctorRequestDTO toDTO(Doctor doctor);
    DoctorResponseDTO toResponseDTO(Doctor doctor);
}
