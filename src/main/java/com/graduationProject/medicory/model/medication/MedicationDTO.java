package com.graduationProject.medicory.model.medication;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationDTO {
    private Long id;

    @NotBlank(message = "Medicine name is required")
    @Size(max = 100, message = "Medicine name must be at most 100 characters long")
    private String medicineName;

    @NotBlank(message = "Dose is required")
    @Size(max = 50, message = "Dose must be at most 50 characters long")
    private String dose;

    @Min(value = 1, message = "Frequency must be at least 1")
    private int frequency;

    @Size(max = 200, message = "Side effects must be at most 200 characters long")
    private String sideEffects;

    @Size(max = 200, message = "Tips must be at most 200 characters long")
    private String tips;
}
