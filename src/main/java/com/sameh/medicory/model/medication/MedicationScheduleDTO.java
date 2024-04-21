package com.sameh.medicory.model.medication;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MedicationScheduleDTO {
    private String medicineName;
    private String dose;
    private int frequency;
    private LocalDateTime medicineCreatedAt;
    private LocalDateTime medicineUpdatedAt;
}
