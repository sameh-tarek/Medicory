package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.testsEntities.ImagingTest;
import com.sameh.medicory.model.tests.ImagingTestRequestDTO;
import com.sameh.medicory.model.tests.ImagingTestResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImagingTestMapper {
    ImagingTest toEntity(ImagingTestRequestDTO imagingTestRequestDTO);
    ImagingTestResponseDTO toDTO(ImagingTest imagingTest);
}
