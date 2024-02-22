package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.otherEntities.ChronicDiseases;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesDTO;

public interface ChronicDiseasesMapper {
    public ChronicDiseases toEntity(ChronicDiseasesDTO chronicDiseasesDTO);
    public ChronicDiseasesDTO toDTO(ChronicDiseases chronicDiseases);
}
