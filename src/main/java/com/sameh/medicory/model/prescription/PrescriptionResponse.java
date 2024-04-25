package com.sameh.medicory.model.prescription;

import com.sameh.medicory.model.medication.CurrentScheduleRequest;
import com.sameh.medicory.model.medication.MedicationDTO;
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
    private String doctorName;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
