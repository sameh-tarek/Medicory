package com.graduationProject.medicory.mapper;

import com.graduationProject.medicory.entity.medicationEntities.Medication;
import com.graduationProject.medicory.model.medication.MedicationDTO;
import com.graduationProject.medicory.model.medication.MedicationResponseDTO;


public interface MedicationMapper {
    Medication toEntity(MedicationDTO medicationDTO);
    MedicationDTO toDTO(Medication medication);

    MedicationResponseDTO toDTo(Medication medication);
}
