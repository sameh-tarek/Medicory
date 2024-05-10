package com.graduationProject.medicory.mapper.otherMappers;

import com.graduationProject.medicory.entity.otherEntities.Surgery;
import com.graduationProject.medicory.model.surgery.SurgeryRequestDTO;
import com.graduationProject.medicory.model.surgery.SurgeryResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SurgeryMapper {
    Surgery toEntity(SurgeryRequestDTO surgeryRequestDTO);
    SurgeryResponseDTO toDTO(Surgery surgery);
}
