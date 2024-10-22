package com.graduationProject.medicory.mapper.usersMappers;


import com.graduationProject.medicory.entity.usersEntities.Doctor;
import com.graduationProject.medicory.model.users.doctor.DoctorDTO;
import com.graduationProject.medicory.model.users.doctor.DoctorRequestDTO;
import com.graduationProject.medicory.model.users.doctor.DoctorResponseDTO;
import com.graduationProject.medicory.model.users.doctor.DoctorSearchResponseDTO;


public interface DoctorMapper {
    Doctor toRequestEntity(DoctorRequestDTO doctor);
    DoctorDTO toDTO(Doctor doctor);
    DoctorResponseDTO toResponseDTO(Doctor doctor);
    DoctorSearchResponseDTO toSearchResponseDTO(Doctor doctor);
}
