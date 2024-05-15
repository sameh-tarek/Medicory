package com.graduationProject.medicory.mapper.testsMappers;

import com.graduationProject.medicory.entity.testsEntities.LabTest;
import com.graduationProject.medicory.model.tests.LabTestRequestDTO;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;

public interface LabTestMapper {
    LabTest toEntity(LabTestRequestDTO labTestRequestDTO);
    LabTestResponseDTO toDTO(LabTest labTest);
}
