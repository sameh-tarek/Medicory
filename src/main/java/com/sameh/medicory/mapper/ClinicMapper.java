package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.usersEntities.Clinic;
import com.sameh.medicory.model.users.ClinicDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClinicMapper {
    Clinic toEntity(ClinicDTO clinicDTO);
    ClinicDTO toDto(Clinic clinic);
    List<Clinic> toEntity(List<ClinicDTO> clinicDTOs);
    List<ClinicDTO> toDto(List<Clinic> clinics);



}
