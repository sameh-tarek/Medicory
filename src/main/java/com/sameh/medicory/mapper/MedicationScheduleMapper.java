package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.medicationEntities.Medication;
import com.sameh.medicory.model.medication.MedicationScheduleDTO;

public interface MedicationScheduleMapper {
    MedicationScheduleDTO toDTO(Medication medication);
}
