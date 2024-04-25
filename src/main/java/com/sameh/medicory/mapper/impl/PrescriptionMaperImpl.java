package com.sameh.medicory.mapper.impl;

import com.sameh.medicory.entity.medicationEntities.Prescription;
import com.sameh.medicory.entity.usersEntities.Doctor;
import com.sameh.medicory.mapper.MedicationMapper;
import com.sameh.medicory.mapper.PrescriptionMaper;
import com.sameh.medicory.model.prescription.PrescriptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class PrescriptionMaperImpl implements PrescriptionMaper {
    private final MedicationMapper medicationMapper;
    @Override
    public PrescriptionResponse toResponse(Prescription prescription) {

        return PrescriptionResponse.builder()
                .prescriptionId(prescription.getId())
                .doctorName(getDoctorName(prescription))
                .createdAt(prescription.getCreatedAt())
                .updatedAt(prescription.getUpdatedAt())
                .status(prescription.isPrescriptionStatus())
                .build();
    }

    private String getDoctorName(Prescription prescription) {
        Doctor doctor= prescription.getDoctor();
        return doctor.getFirstName() + " " + doctor.getLastName();
    }
}
