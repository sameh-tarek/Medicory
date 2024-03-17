package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.labTestsEntities.LabTest;
import com.sameh.medicory.model.labtests.LabTestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LabTestMapper {
    public LabTest toEntity(LabTestDTO labTestDTO);
    public LabTestDTO toDTO(LabTest labTest);
}
