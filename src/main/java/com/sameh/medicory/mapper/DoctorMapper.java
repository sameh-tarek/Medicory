package com.sameh.medicory.mapper;


import com.sameh.medicory.entity.usersEntities.Doctor;
import com.sameh.medicory.model.users.Doctor.DoctorRequestDTO;
import com.sameh.medicory.model.users.Doctor.DoctorResponseDTO;
import org.mapstruct.Mapper;



public interface DoctorMapper {
    Doctor toEntity(DoctorRequestDTO doctorRequestDTO);
    DoctorRequestDTO toDTO(Doctor doctor);
    DoctorResponseDTO toResponseDTO(Doctor doctor);
}
