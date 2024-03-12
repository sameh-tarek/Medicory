package com.sameh.medicory.mapper.impl;

import com.sameh.medicory.entity.labTestsEntities.LabTest;
import com.sameh.medicory.mapper.LabTestMapper;
import com.sameh.medicory.model.labtests.LabTestDTO;
import com.sameh.medicory.utils.OwnerContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LabTestMapperImpl implements LabTestMapper {
    private final OwnerContext ownerContext;
    @Override
    public LabTest toEntity(LabTestDTO labTestDTO) {
        LabTest labTest = new LabTest();
        labTest.setDescription(labTestDTO.getDescription());
        labTest.setImageResult(labTestDTO.getImageResult());
        labTest.setResultNotes(labTestDTO.getResultNotes());
        labTest.setOwner(ownerContext.getCurrentOwner());
        return labTest;
    }
    @Override
    public LabTestDTO toDTO(LabTest labTest) {
        LabTestDTO labTestDTO = new LabTestDTO();
        labTestDTO.setDescription(labTest.getDescription());
        labTestDTO.setResultNotes(labTest.getResultNotes());
        labTestDTO.setImageResult(labTest.getImageResult());
        return labTestDTO;
    }
}
