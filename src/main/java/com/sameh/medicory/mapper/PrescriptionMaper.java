package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.medicationEntities.Prescription;
import com.sameh.medicory.model.prescription.PrescriptionResponse;

public interface PrescriptionMaper {
    PrescriptionResponse toResponse(Prescription prescription);
}
