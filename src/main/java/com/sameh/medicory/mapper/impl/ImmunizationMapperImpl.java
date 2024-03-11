package com.sameh.medicory.mapper.impl;

import com.sameh.medicory.entity.otherEntities.Immunization;
import com.sameh.medicory.mapper.ImmunizationMapper;
import com.sameh.medicory.model.ImmunizationDTO;
import com.sameh.medicory.utils.OwnerContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImmunizationMapperImpl implements ImmunizationMapper {
    private final OwnerContext ownerContext;
    @Override
    public Immunization toEntity(ImmunizationDTO immunizationDTO) {
        Immunization immunization = new Immunization();
        immunization.setName(immunizationDTO.getName());
        immunization.setInformation(immunizationDTO.getInformation());
        immunization.setOwner(ownerContext.getCurrentOwner());
        return immunization;
    }

    @Override
    public ImmunizationDTO toDTO(Immunization immunization) {
        ImmunizationDTO immunizationDTO = new ImmunizationDTO();
        immunizationDTO.setName(immunization.getName());
        immunizationDTO.setInformation(immunization.getInformation());
        return immunizationDTO;
    }
}
