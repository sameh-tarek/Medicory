package com.graduationProject.medicory.mapper;

import com.graduationProject.medicory.entity.medicationEntities.Prescription;
import com.graduationProject.medicory.model.prescription.PrescriptionResponse;

public interface PrescriptionMapper {
    PrescriptionResponse toResponse(Prescription prescription);
}
