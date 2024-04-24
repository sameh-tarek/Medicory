package com.sameh.medicory.service.pharmacy;

import com.sameh.medicory.model.medication.CurrentScheduleRequest;
import com.sameh.medicory.model.medication.MedicationRequestDTO;
import com.sameh.medicory.model.prescription.PrescriptionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PharmacyService {

    List<PrescriptionResponse> getAllPrescription(Long ownerId);
    List<PrescriptionResponse> getActivePrescription(Long ownerId);

    PrescriptionResponse getPrescriptionById(Long id);

    String createTreatmentSchedule(CurrentScheduleRequest currentScheduleRequest);
}
