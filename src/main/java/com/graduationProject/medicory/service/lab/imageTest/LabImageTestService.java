package com.graduationProject.medicory.service.lab.imageTest;

import com.graduationProject.medicory.model.tests.ImagingTestResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LabImageTestService {

    List<ImagingTestResponseDTO> getAllImageTestsOfPrescription(Long prescriptionId);

    List<ImagingTestResponseDTO> getActiveImageTestsOfPrescription(Long prescriptionId);

    String uploadImageTestResult(MultipartFile file, Long imageTestId) throws IOException;

    String deleteImageTestResult(Long testId) throws IOException;
}
