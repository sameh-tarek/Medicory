package com.graduationProject.medicory.service.lab;

import com.graduationProject.medicory.model.prescription.PrescriptionResponse;

import java.util.List;

public interface LabService {
    List<PrescriptionResponse> getAllPrescriptionsNeedLab(String userCode);
    List<PrescriptionResponse> getActivePrescriptionsNeedLab(String userCode);
}