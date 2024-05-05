package com.graduationProject.medicory.model.prescription;

import com.graduationProject.medicory.model.medication.MedicationResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PrescriptionResponseDTO {
    List<MedicationResponseDTO> medications;
}
