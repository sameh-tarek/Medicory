package com.graduationProject.medicory.model.prescription;


import com.graduationProject.medicory.model.medication.MedicationDTO;
import com.graduationProject.medicory.model.tests.ImagingTestRequestDTO;
import com.graduationProject.medicory.model.tests.LabTestRequestDTO;
import lombok.Data;

import java.util.List;

@Data
public class PrescriptionRequestDTO {
    List<MedicationDTO> medications;
    List<LabTestRequestDTO> labTests;
    List<ImagingTestRequestDTO> imagingTests;
}
