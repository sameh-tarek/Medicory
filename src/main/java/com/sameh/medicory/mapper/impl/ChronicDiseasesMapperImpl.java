package com.sameh.medicory.mapper.impl;

import com.sameh.medicory.entity.otherEntities.ChronicDiseases;
import com.sameh.medicory.exception.RecordNotFoundException;
import com.sameh.medicory.mapper.ChronicDiseasesMapper;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesDTO;
import com.sameh.medicory.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChronicDiseasesMapperImpl implements ChronicDiseasesMapper {

    private final OwnerRepository ownerRepository;

    @Override
    public ChronicDiseases toEntity(ChronicDiseasesDTO chronicDiseasesDTO) {
        ChronicDiseases chronicDiseases = new ChronicDiseases();
        chronicDiseases.setName(chronicDiseasesDTO.getName());
        chronicDiseases.setInformation(chronicDiseases.getInformation());
        chronicDiseases.setOwner(ownerRepository.findById(chronicDiseases.getId())
                .orElseThrow(() -> new RecordNotFoundException("Not Found User With Id : " + chronicDiseasesDTO.getOwnerId())));
        return chronicDiseases;
    }

    @Override
    public ChronicDiseasesDTO toDTO(ChronicDiseases chronicDiseases) {
        ChronicDiseasesDTO chronicDiseasesDTO = new ChronicDiseasesDTO();
        chronicDiseasesDTO.setName(chronicDiseases.getName());
        chronicDiseasesDTO.setInformation(chronicDiseases.getInformation());
        chronicDiseasesDTO.setOwnerId(chronicDiseases.getOwner().getId());
        return chronicDiseasesDTO;
    }
}
