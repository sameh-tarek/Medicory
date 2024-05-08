package com.graduationProject.medicory.mapper;

import com.graduationProject.medicory.entity.testsEntities.ImagingTest;
import com.graduationProject.medicory.model.tests.ImagingTestRequestDTO;
import com.graduationProject.medicory.model.tests.ImagingTestResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImagingTestMapper {
    ImagingTest toEntity(ImagingTestRequestDTO imagingTestRequestDTO);
    ImagingTestResponseDTO toDTO(ImagingTest imagingTest);
}
