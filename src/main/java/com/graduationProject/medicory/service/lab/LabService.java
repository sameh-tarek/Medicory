package com.graduationProject.medicory.service.lab;

import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LabService {

    List<PrescriptionResponse> getAllPrescriptions(String userCode);
    List<PrescriptionResponse> getActivePrescriptions(String userCode);

    List<LabTestResponseDTO> getAllTestsOfPrescription(Long prescriptionId);

    List<LabTestResponseDTO> getActiveTests(Long prescriptionId);
}
