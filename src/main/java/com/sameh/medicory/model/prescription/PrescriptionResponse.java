package com.sameh.medicory.model.prescription;

import com.sameh.medicory.model.medication.MedicationScheduleDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
public class PrescriptionResponse {
    private Long prescriptionId;
    private boolean status;
    private List<MedicationScheduleDTO> medications;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
