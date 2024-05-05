package com.graduationProject.medicory.model.medication;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MedicationResponseDTO {
    private Long id;
    private String dose;
    private int frequency;
    private String tips;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
