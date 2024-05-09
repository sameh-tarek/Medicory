package com.graduationProject.medicory.mapper;


import com.graduationProject.medicory.entity.usersEntities.Doctor;
import com.graduationProject.medicory.model.users.doctor.DoctorRequestDTO;
import com.graduationProject.medicory.model.users.doctor.DoctorDTO;
import com.graduationProject.medicory.model.users.doctor.DoctorResponseDTO;


public interface DoctorMapper {
    Doctor toEntity(DoctorDTO doctorDTO);
    Doctor toRequestEntity(DoctorRequestDTO doctor);
    DoctorDTO toDTO(Doctor doctor);
    DoctorResponseDTO toResponseDTO(Doctor doctor);

}
