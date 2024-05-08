package com.graduationProject.medicory.mapper;

import com.graduationProject.medicory.entity.otherEntities.Immunization;
import com.graduationProject.medicory.model.immunization.ImmunizationRequestDTO;
import com.graduationProject.medicory.model.immunization.ImmunizationResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImmunizationMapper {
    Immunization toEntity(ImmunizationRequestDTO immunizationRequestDTO);
    ImmunizationResponseDTO toDTO(Immunization immunization);
}
