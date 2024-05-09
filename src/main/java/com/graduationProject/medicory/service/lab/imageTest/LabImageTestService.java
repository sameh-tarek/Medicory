package com.graduationProject.medicory.service.lab.imageTest;

import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;

import java.util.List;

public interface LabImageTestService {
    List<PrescriptionResponse> getAllPrescriptionsHaveImagingTests(String userCode);

    List<PrescriptionResponse> getActivePrescriptionsHaveImagingTests(String userCode);
    List<LabTestResponseDTO> getAllImageTestsOfPrescription(Long prescriptionId);

    List<LabTestResponseDTO> getActiveImageTests(Long prescriptionId);
}
