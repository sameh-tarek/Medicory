package com.sameh.medicory.mapper.impl;

import com.sameh.medicory.entity.medicationEntities.Medication;
import com.sameh.medicory.mapper.MedicationMapper;
import com.sameh.medicory.model.medication.MedicationDTO;
import org.springframework.stereotype.Component;

@Component
public class MedicationMapperImpl implements MedicationMapper {
    @Override
    public Medication toEntity(MedicationDTO medicationDTO) {
        return Medication.builder()
                .dose(medicationDTO.getDose())
                .frequency(medicationDTO.getFrequency())
                .tips(medicationDTO.getTips())
                .build();
    }

    @Override
    public MedicationDTO toDTO(Medication medication) {
        return MedicationDTO.builder()
                .medicineName(medication.getMedicine().getName())
                .dose(medication.getDose())
                .frequency(medication.getFrequency())
                .sideEffects(medication.getMedicine().getSideEffects())
                .tips(medication.getTips())
                .build();
    }
}
