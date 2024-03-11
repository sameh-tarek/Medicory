package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.otherEntities.Surgery;
import com.sameh.medicory.model.SurgeryDTO;

public interface SurgeryMapper {
    Surgery toEntity(SurgeryDTO surgeryDTO);
    SurgeryDTO toDTO(Surgery surgery);
}
