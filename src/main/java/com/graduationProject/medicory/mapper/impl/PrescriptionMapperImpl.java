package com.graduationProject.medicory.mapper.impl;

import com.graduationProject.medicory.entity.medicationEntities.Prescription;
import com.graduationProject.medicory.entity.usersEntities.Doctor;
import com.graduationProject.medicory.mapper.medicationsMappers.MedicationMapper;
import com.graduationProject.medicory.mapper.medicationsMappers.PrescriptionMapper;
import com.graduationProject.medicory.mapper.testsMappers.ImagingTestMapper;
import com.graduationProject.medicory.mapper.testsMappers.LabTestMapper;
import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
import com.graduationProject.medicory.model.prescription.PrescriptionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PrescriptionMapperImpl implements PrescriptionMapper {
    private final MedicationMapper medicationMapper;
    private final LabTestMapper labTestMapper;
    private final ImagingTestMapper imagingTestMapper;

    @Override
    public PrescriptionResponse toResponse(Prescription prescription) {
        return PrescriptionResponse.builder()
                .prescriptionId(prescription.getId())
                //.doctorName(getDoctorName(prescription))
                .medicationStatus(prescription.isMedicationStatus())
                .prescriptionStatus(prescription.isPrescriptionStatus())
                .createdAt(prescription.getCreatedAt())
                .updatedAt(prescription.getUpdatedAt())
                .build();
    }

    @Override
    public PrescriptionResponseDTO toResponseDTO(Prescription prescription) {
        return PrescriptionResponseDTO.builder()
                .prescriptionResponse(toResponse(prescription))
                .medications(prescription.getMedications().stream()
                        .map(medicationMapper::toDTo)
                        .collect(Collectors.toList()))
                .labTests(prescription.getTests().stream()
                        .map(labTestMapper::toDTO)
                        .collect(Collectors.toList()))
                .imagingTests(prescription.getImagingTests().stream()
                        .map(imagingTestMapper::toDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    private String getDoctorName(Prescription prescription) {
        Doctor doctor = prescription.getDoctor();
        return doctor.getFirstName() + " " + doctor.getLastName();
    }
}
