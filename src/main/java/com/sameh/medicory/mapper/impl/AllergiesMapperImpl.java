package com.sameh.medicory.mapper.impl;

import com.sameh.medicory.entity.otherEntities.Allergies;
import com.sameh.medicory.mapper.AllergiesMapper;
import com.sameh.medicory.model.AllergiesDTO;
import com.sameh.medicory.utils.OwnerContext;
import com.sameh.medicory.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AllergiesMapperImpl implements AllergiesMapper {
    private final OwnerContext ownerContext;
    @Override
    public Allergies toEntity(AllergiesDTO allergiesDTO) {
        Allergies allergies = new Allergies();
        allergies.setOwner(ownerContext.getCurrentOwner());
        allergies.setName(allergiesDTO.getName());
        allergies.setInformation(allergiesDTO.getInformation());
        return allergies;
    }

    @Override
    public AllergiesDTO toDTO(Allergies allergies) {
        AllergiesDTO allergiesDTO = new AllergiesDTO();
        allergiesDTO.setName(allergies.getName());
        allergiesDTO.setInformation(allergies.getInformation());
        return allergiesDTO;
    }
}
