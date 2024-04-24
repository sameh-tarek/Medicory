package com.sameh.medicory.model.medication;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationDTO {
    private String medicineName;

    private String dose;

    private int frequency;

    private String sideEffects;

    private String tips;
}
