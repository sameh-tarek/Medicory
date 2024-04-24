package com.sameh.medicory.model.prescription;


import com.sameh.medicory.model.medication.MedicationDTO;
import lombok.Data;

import java.util.List;

@Data
public class PrescriptionRequestDTO {
    List<MedicationDTO> medications;
}
