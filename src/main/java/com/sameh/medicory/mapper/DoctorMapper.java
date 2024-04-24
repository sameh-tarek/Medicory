package com.sameh.medicory.mapper;


import com.sameh.medicory.entity.usersEntities.Doctor;
import com.sameh.medicory.model.users.DoctorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface DoctorMapper {
    Doctor toEntity(DoctorDTO doctorDTO);
    DoctorDTO toDTO(Doctor doctor);
}
