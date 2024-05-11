package com.graduationProject.medicory.mapper.otherMappers;

import com.graduationProject.medicory.entity.otherEntities.Allergies;
import com.graduationProject.medicory.model.allergies.AllergiesRequestDTO;
import com.graduationProject.medicory.model.allergies.AllergiesResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AllergiesMapper {
    public Allergies toEntity(AllergiesRequestDTO allergiesRequestDTO);
    public AllergiesResponseDTO toDTO(Allergies allergies);
}