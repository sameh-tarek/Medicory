package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.testsEntities.ImagingTest;
import com.sameh.medicory.model.tests.ImagingTestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImagingTestMapper {
    ImagingTest toEntity(ImagingTestDTO imagingTestDTO);
    ImagingTestDTO toDTO(ImagingTest imagingTest);
}
