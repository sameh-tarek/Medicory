package com.graduationProject.medicory.service.pharmacy;

import com.graduationProject.medicory.model.VoiceRecord.VoiceRecordResponse;
import com.graduationProject.medicory.model.medication.CurrentScheduleRequest;
import com.graduationProject.medicory.model.medication.MedicationDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;

public interface PharmacyCurrentScheduleService {
    String createTreatmentSchedule(String userCode, CurrentScheduleRequest currentScheduleRequest);

    List<MedicationDTO> getMedicationOfPrescription(String userCode, Long prescriptionId);

    String createVoiceRecord(String userCode, MultipartFile file, Long medicationId) throws IOException, UnsupportedAudioFileException;

    String deleteVoiceRecord(String userCode, Long medicationId, Long recordId) throws IOException;

    String deleteTreatmentFromCurrentSchedule(String userCode, Long medicationId);

    List<VoiceRecordResponse> getVoiceRecords(String userCode, Long medicationId);
}