package com.graduationProject.medicory.service.lab;

import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
import com.graduationProject.medicory.model.prescription.PrescriptionResponseDTO;

import java.util.List;

public interface LabService {
    List<PrescriptionResponseDTO> getAllPrescriptionsNeedLab(String userCode);
    List<PrescriptionResponseDTO> getActivePrescriptionsNeedLab(String userCode);
}
