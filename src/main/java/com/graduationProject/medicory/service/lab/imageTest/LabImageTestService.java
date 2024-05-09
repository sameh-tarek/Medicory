package com.graduationProject.medicory.service.lab.imageTest;

import com.graduationProject.medicory.model.tests.ImagingTestResponseDTO;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;

import java.util.List;

public interface LabImageTestService {

    List<ImagingTestResponseDTO> getAllImageTestsOfPrescription(Long prescriptionId);

    List<ImagingTestResponseDTO> getActiveImageTestsOfPrescription(Long prescriptionId);
}
