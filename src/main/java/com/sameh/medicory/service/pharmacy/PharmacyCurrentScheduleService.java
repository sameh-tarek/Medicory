package com.sameh.medicory.service.pharmacy;

import com.sameh.medicory.model.medication.CurrentScheduleRequest;
import com.sameh.medicory.model.medication.MedicationDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PharmacyCurrentScheduleService {
    String createTreatmentSchedule(String userCode, CurrentScheduleRequest currentScheduleRequest);

    List<MedicationDTO> getMedicationOfPrescription(String userCode, Long prescriptionId);

//    String createVoiceRecord( MultipartFile file, Long medicationId);
}