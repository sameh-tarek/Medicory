package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.medicationEntities.Medication;
import com.sameh.medicory.model.medication.MedicationRequestDTO;
import com.sameh.medicory.model.medication.MedicationResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicationMapper {
    Medication toEntity(MedicationRequestDTO medicationRequestDTO);
    MedicationResponseDTO toDTO(Medication medication);
}
