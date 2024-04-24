package com.sameh.medicory.mapper.impl;

import com.sameh.medicory.entity.medicationEntities.Prescription;
import com.sameh.medicory.mapper.MedicationMapper;
import com.sameh.medicory.mapper.MedicationScheduleMapper;
import com.sameh.medicory.mapper.PrescriptionMaper;
import com.sameh.medicory.model.medication.MedicationScheduleDTO;
import com.sameh.medicory.model.prescription.PrescriptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PrescriptionMaperImpl implements PrescriptionMaper {
    private final MedicationScheduleMapper medicationScheduleMapper;
    @Override
    public PrescriptionResponse toResponse(Prescription prescription) {
        List<MedicationScheduleDTO> medicationsResponse =
                prescription.getMedications().stream()
                        .map(medication -> medicationScheduleMapper.toDTO(medication))
                        .toList();
        return PrescriptionResponse.builder()
                .prescriptionId(prescription.getId())
                .medications(medicationsResponse)
                .createdAt(prescription.getCreatedAt())
                .updatedAt(prescription.getUpdatedAt())
                .status(prescription.isStatus())
                .build();
    }
}
