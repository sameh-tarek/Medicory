package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.testsEntities.LabTest;
import com.sameh.medicory.model.tests.LabTestRequestDTO;
import com.sameh.medicory.model.tests.LabTestResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LabTestMapper {
    LabTest toEntity(LabTestRequestDTO labTestRequestDTO);
    LabTestResponseDTO toDTO(LabTest labTest);
}
