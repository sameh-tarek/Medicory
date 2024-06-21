package com.graduationProject.medicory.service.pharmacy;

import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PharmacyPrescriptionService {

    List<PrescriptionResponse> getAllPrescriptionNeedPharmacy(String userCode);
    List<PrescriptionResponse> getActivePrescriptionNeedPharmacy(String userCode);
    PrescriptionResponse getPrescriptionById(String userCode, Long id);
}
