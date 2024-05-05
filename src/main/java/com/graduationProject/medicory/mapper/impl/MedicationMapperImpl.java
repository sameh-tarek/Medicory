package com.graduationProject.medicory.mapper.impl;

import com.graduationProject.medicory.entity.medicationEntities.Medication;
import com.graduationProject.medicory.mapper.MedicationMapper;
import com.graduationProject.medicory.model.medication.MedicationDTO;
import com.graduationProject.medicory.model.medication.MedicationResponseDTO;
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
                .id(medication.getId())
                .medicineName(medication.getMedicine().getName())
                .dose(medication.getDose())
                .frequency(medication.getFrequency())
                .sideEffects(medication.getMedicine().getSideEffects())
                .tips(medication.getTips())
                .build();
    }

    @Override
    public MedicationResponseDTO toDTo(Medication medication) {
        return MedicationResponseDTO.builder()
                .id(medication.getId())
                .dose(medication.getDose())
                .frequency(medication.getFrequency())
                .tips(medication.getTips())
                .createdAt(medication.getCreatedAt())
                .updatedAt(medication.getUpdatedAt())
                .build();
    }
}
