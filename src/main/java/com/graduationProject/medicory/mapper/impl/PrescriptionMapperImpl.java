package com.graduationProject.medicory.mapper.impl;

import com.graduationProject.medicory.entity.medicationEntities.Prescription;
import com.graduationProject.medicory.entity.usersEntities.Doctor;
import com.graduationProject.medicory.mapper.medicationsMappers.MedicationMapper;
import com.graduationProject.medicory.mapper.medicationsMappers.PrescriptionMapper;
import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class PrescriptionMapperImpl implements PrescriptionMapper {
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
