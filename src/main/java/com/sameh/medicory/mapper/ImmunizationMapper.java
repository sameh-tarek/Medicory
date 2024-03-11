package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.otherEntities.Immunization;
import com.sameh.medicory.model.ImmunizationDTO;

public interface ImmunizationMapper {
    Immunization toEntity(ImmunizationDTO immunizationDTO);

    ImmunizationDTO toDTO(Immunization immunization);
}
