package com.sameh.medicory.mapper.impl;

import com.sameh.medicory.entity.otherEntities.ChronicDiseases;
import com.sameh.medicory.mapper.ChronicDiseasesMapper;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesDTO;
import com.sameh.medicory.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChronicDiseasesMapperImpl implements ChronicDiseasesMapper {

    private final SecurityUtils securityUtils;

    @Override
    public ChronicDiseases toEntity(ChronicDiseasesDTO chronicDiseasesDTO) {
        ChronicDiseases chronicDiseases = new ChronicDiseases();
        chronicDiseases.setName(chronicDiseasesDTO.getName());
        chronicDiseases.setInformation(chronicDiseasesDTO.getInformation());
        chronicDiseases.setOwner(securityUtils.getCurrentOwner());
        return chronicDiseases;
    }

    @Override
    public ChronicDiseasesDTO toDTO(ChronicDiseases chronicDiseases) {
        ChronicDiseasesDTO chronicDiseasesDTO = new ChronicDiseasesDTO();
        chronicDiseasesDTO.setName(chronicDiseases.getName());
        chronicDiseasesDTO.setInformation(chronicDiseases.getInformation());
        return chronicDiseasesDTO;
    }
}
