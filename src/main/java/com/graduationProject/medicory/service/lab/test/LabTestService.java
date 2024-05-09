package com.graduationProject.medicory.service.lab.test;

import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LabTestService {


    List<LabTestResponseDTO> getAllTestsOfPrescription(Long prescriptionId);

    List<LabTestResponseDTO> getActiveTestsOfPrescription(Long prescriptionId);

}
