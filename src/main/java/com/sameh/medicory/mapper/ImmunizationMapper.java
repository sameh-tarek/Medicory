package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.otherEntities.Immunization;
import com.sameh.medicory.model.immunization.ImmunizationRequestDTO;
import com.sameh.medicory.model.immunization.ImmunizationResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImmunizationMapper {
    Immunization toEntity(ImmunizationRequestDTO immunizationRequestDTO);
    ImmunizationResponseDTO toDTO(Immunization immunization);
}
