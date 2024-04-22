package com.sameh.medicory.model.prescription;

import com.sameh.medicory.model.medication.MedicationResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PrescriptionResponseDTO {
    List<MedicationResponseDTO> medications;
}
