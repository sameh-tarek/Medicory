package com.graduationProject.medicory.mapper;

import com.graduationProject.medicory.entity.testsEntities.LabTest;
import com.graduationProject.medicory.model.tests.LabTestRequestDTO;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LabTestMapper {
    LabTest toEntity(LabTestRequestDTO labTestRequestDTO);
    LabTestResponseDTO toDTO(LabTest labTest);
}