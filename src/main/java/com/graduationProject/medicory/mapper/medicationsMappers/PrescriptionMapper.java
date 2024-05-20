package com.graduationProject.medicory.mapper.medicationsMappers;

import com.graduationProject.medicory.entity.medicationEntities.Prescription;
import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
import com.graduationProject.medicory.model.prescription.PrescriptionResponseDTO;

public interface PrescriptionMapper {
    PrescriptionResponse toResponse(Prescription prescription);
    PrescriptionResponseDTO toResponseDTO(Prescription prescription);
}
