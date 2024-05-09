package com.graduationProject.medicory.service.lab.imageTest;

import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabImageTestServiceImpl implements LabImageTestService {

    @Override
    public List<PrescriptionResponse> getAllPrescriptionsHaveImagingTests(String userCode) {
        return null;
    }

    @Override
    public List<PrescriptionResponse> getActivePrescriptionsHaveImagingTests(String userCode) {
        return null;
    }

    @Override
    public List<LabTestResponseDTO> getAllImageTestsOfPrescription(Long prescriptionId) {
        return null;
    }

    @Override
    public List<LabTestResponseDTO> getActiveImageTests(Long prescriptionId) {
        return null;
    }
}
