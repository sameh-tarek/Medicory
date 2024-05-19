package com.graduationProject.medicory.model.prescription;

import com.graduationProject.medicory.model.medication.MedicationResponseDTO;
import com.graduationProject.medicory.model.tests.ImagingTestRequestDTO;
import com.graduationProject.medicory.model.tests.ImagingTestResponseDTO;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PrescriptionResponseDTO {
    PrescriptionResponse prescriptionResponse;
    List<MedicationResponseDTO> medications;
    List<LabTestResponseDTO> labTests;
    List<ImagingTestResponseDTO> imagingTests;
}
