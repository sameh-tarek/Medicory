package com.graduationProject.medicory.model.prescription;


import com.graduationProject.medicory.model.medication.MedicationDTO;
import lombok.Data;

import java.util.List;

@Data
public class PrescriptionRequestDTO {
    List<MedicationDTO> medications;
}
