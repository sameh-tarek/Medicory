package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.otherEntities.Allergies;
import com.sameh.medicory.model.AllergiesDTO;
public interface AllergiesMapper {
    public Allergies toEntity(AllergiesDTO allergiesDTO);
    public AllergiesDTO toDTO(Allergies allergies);
}
