package com.sameh.medicory.model.medication;

import lombok.Data;

@Data
public class MedicationRequestDTO {
    private String medicineName;
    private String dose;
    private int frequency;
    private String sideEffects;
    private String tips;
}
