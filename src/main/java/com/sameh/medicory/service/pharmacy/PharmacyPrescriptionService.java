package com.sameh.medicory.service.pharmacy;

import com.sameh.medicory.model.medication.CurrentScheduleRequest;
import com.sameh.medicory.model.prescription.PrescriptionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PharmacyPrescriptionService {

    List<PrescriptionResponse> getAllPrescription(String userCode);
    List<PrescriptionResponse> getActivePrescription(String userCode);

    PrescriptionResponse getPrescriptionById(String userCode,Long id);

}
