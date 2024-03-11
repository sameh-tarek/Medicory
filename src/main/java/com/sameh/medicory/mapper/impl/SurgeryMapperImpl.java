package com.sameh.medicory.mapper.impl;

import com.sameh.medicory.entity.otherEntities.Surgery;
import com.sameh.medicory.mapper.SurgeryMapper;
import com.sameh.medicory.model.SurgeryDTO;
import com.sameh.medicory.utils.OwnerContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SurgeryMapperImpl implements SurgeryMapper {
    private final OwnerContext ownerContext;

    @Override
    public Surgery toEntity(SurgeryDTO surgeryDTO) {
        Surgery surgery = new Surgery();
        surgery.setDescription(surgeryDTO.getDescription());
        surgery.setOwner(ownerContext.getCurrentOwner());
        return surgery;
    }

    @Override
    public SurgeryDTO toDTO(Surgery surgery) {
        SurgeryDTO surgeryDTO = new SurgeryDTO();
        surgeryDTO.setDescription(surgery.getDescription());
        return surgeryDTO;
    }
}
