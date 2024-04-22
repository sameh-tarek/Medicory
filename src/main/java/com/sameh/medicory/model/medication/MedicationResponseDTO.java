package com.sameh.medicory.model.medication;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MedicationResponseDTO {
    private Long id;
    private String dose;
    private int frequency;
    private String tips;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
