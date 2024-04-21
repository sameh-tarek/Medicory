package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.medicationEntities.Medication;
import com.sameh.medicory.model.medication.MedicationRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicationMapper {
    Medication toEntity(MedicationRequestDTO medicationRequestDTO);
    MedicationRequestDTO toDTO(Medication medication);
}
