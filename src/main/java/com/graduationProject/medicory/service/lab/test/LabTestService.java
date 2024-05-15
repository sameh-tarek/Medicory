package com.graduationProject.medicory.service.lab.test;

import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LabTestService {


    List<LabTestResponseDTO> getAllTestsOfPrescription(Long prescriptionId);
    List<LabTestResponseDTO> getActiveTestsOfPrescription(Long prescriptionId);

    String uploadTestResult(MultipartFile file, Long testId) throws IOException;

    String deleteTestResult(Long testId) throws IOException;
}
