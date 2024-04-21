package com.sameh.medicory.mapper.impl;

import com.sameh.medicory.entity.medicationEntities.Medication;
import com.sameh.medicory.mapper.MedicationScheduleMapper;
import com.sameh.medicory.model.medication.MedicationScheduleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MedicationScheduleMapperImpl implements MedicationScheduleMapper {
    @Override
    public MedicationScheduleDTO toDTO(Medication medication) {
        return MedicationScheduleDTO.builder()
                .medicineName(medication.getMedicine().getName())
                .dose(medication.getDose())
                .frequency(medication.getFrequency())
                .medicineCreatedAt(medication.getCreatedAt())
                .medicineUpdatedAt(medication.getUpdatedAt())
                .build();
    }
}
