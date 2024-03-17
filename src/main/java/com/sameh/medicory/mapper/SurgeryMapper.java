package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.otherEntities.Surgery;
import com.sameh.medicory.model.surgery.SurgeryRequestDTO;
import com.sameh.medicory.model.surgery.SurgeryResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SurgeryMapper {
    Surgery toEntity(SurgeryRequestDTO surgeryRequestDTO);
    SurgeryResponseDTO toDTO(Surgery surgery);
}
