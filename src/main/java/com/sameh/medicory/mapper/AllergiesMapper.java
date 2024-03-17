package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.otherEntities.Allergies;
import com.sameh.medicory.model.allergies.AllergiesRequestDTO;
import com.sameh.medicory.model.allergies.AllergiesResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AllergiesMapper {
    public Allergies toEntity(AllergiesRequestDTO allergiesRequestDTO);
    public AllergiesResponseDTO toDTO(Allergies allergies);
}