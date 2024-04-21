package com.sameh.medicory.model.prescription;


import com.sameh.medicory.model.medication.MedicationRequestDTO;
import lombok.Data;

import java.util.List;

@Data
public class PrescriptionRequestDTO {
    List<MedicationRequestDTO> medications;
}
