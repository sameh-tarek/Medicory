package com.graduationProject.medicory.mapper.impl;

import com.graduationProject.medicory.entity.medicationEntities.Prescription;
import com.graduationProject.medicory.entity.usersEntities.Doctor;
import com.graduationProject.medicory.mapper.MedicationMapper;
import com.graduationProject.medicory.mapper.PrescriptionMaper;
import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
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