package com.graduationProject.medicory.service.pharmacy;

import com.graduationProject.medicory.model.medication.CurrentScheduleRequest;
import com.graduationProject.medicory.model.medication.MedicationDTO;

import java.util.List;

public interface PharmacyCurrentScheduleService {
    String createTreatmentSchedule(String userCode, CurrentScheduleRequest currentScheduleRequest);

    List<MedicationDTO> getMedicationOfPrescription(String userCode, Long prescriptionId);

//    String createVoiceRecord( MultipartFile file, Long medicationId);
}