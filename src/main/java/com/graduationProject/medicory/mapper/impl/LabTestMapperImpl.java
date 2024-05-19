package com.graduationProject.medicory.mapper.impl;

import com.graduationProject.medicory.entity.testsEntities.LabTest;
import com.graduationProject.medicory.mapper.testsMappers.LabTestMapper;
import com.graduationProject.medicory.model.tests.LabTestRequestDTO;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class LabTestMapperImpl implements LabTestMapper {
    @Override
    public LabTest toEntity(LabTestRequestDTO labTestRequestDTO) {
        if ( labTestRequestDTO == null ) {
            return null;
        }

        LabTest labTest = LabTest.builder()
                .name(labTestRequestDTO.getName())
                .description(labTestRequestDTO.getDescription())
                .testNotes(labTestRequestDTO.getResultNotes())
                .status(labTestRequestDTO.isStatus())
                .build();

        return labTest;
    }

    @Override
    public LabTestResponseDTO toDTO(LabTest labTest) {
        if ( labTest == null ) {
            return null;
        }

        LabTestResponseDTO labTestResponseDTO = LabTestResponseDTO.builder()
                        .id(labTest.getId())
                        .name(labTest.getName())
                        .description(labTest.getDescription())
                        .testNotes(labTest.getTestNotes())
                        .imageResult(labTest.getTestResultPath())
                        .status(labTest.isStatus())
                        .createdAt(labTest.getCreatedAt())
                        .updatedAt(labTest.getUpdatedAt())
                        .build();
        return labTestResponseDTO;
    }
}
