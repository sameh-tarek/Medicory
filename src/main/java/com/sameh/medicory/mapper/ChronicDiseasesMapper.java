package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.otherEntities.ChronicDiseases;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesRequestDTO;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChronicDiseasesMapper {
    public ChronicDiseases toEntity(ChronicDiseasesRequestDTO chronicDiseasesRequestDTO);
    public ChronicDiseasesResponseDTO toDTO(ChronicDiseases chronicDiseases);
}
