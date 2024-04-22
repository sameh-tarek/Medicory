package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.medicationEntities.Medication;
import com.sameh.medicory.model.medication.MedicationDTO;

public interface MedicationMapper {
    Medication toEntity(MedicationDTO medicationDTO);
    MedicationDTO toDTO(Medication medication);
}
