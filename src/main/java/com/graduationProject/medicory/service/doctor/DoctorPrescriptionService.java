package com.graduationProject.medicory.service.doctor;

import com.graduationProject.medicory.model.prescription.PrescriptionRequestDTO;
import com.graduationProject.medicory.model.prescription.PrescriptionResponseDTO;

import java.util.List;

public interface DoctorPrescriptionService {
    boolean addNewPrescription(String userCode, PrescriptionRequestDTO prescriptionRequestDTO);
    PrescriptionResponseDTO findPrescriptionById(Long prescriptionId);
    List<PrescriptionResponseDTO> getAllPrescriptions(String userCode);
}
