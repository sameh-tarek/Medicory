package com.graduationProject.medicory.service.pharmacy;

import com.graduationProject.medicory.model.prescription.PrescriptionResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PharmacyPrescriptionService {

    List<PrescriptionResponseDTO> getAllPrescription(String userCode);
    List<PrescriptionResponseDTO> getActivePrescription(String userCode);
    PrescriptionResponseDTO getPrescriptionById(String userCode, Long id);
}
