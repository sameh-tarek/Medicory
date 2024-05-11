package com.graduationProject.medicory.mapper.otherMappers;

import com.graduationProject.medicory.entity.otherEntities.ChronicDiseases;
import com.graduationProject.medicory.model.chronicDisease.ChronicDiseasesRequestDTO;
import com.graduationProject.medicory.model.chronicDisease.ChronicDiseasesResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChronicDiseasesMapper {
    public ChronicDiseases toEntity(ChronicDiseasesRequestDTO chronicDiseasesRequestDTO);
    public ChronicDiseasesResponseDTO toDTO(ChronicDiseases chronicDiseases);
}
