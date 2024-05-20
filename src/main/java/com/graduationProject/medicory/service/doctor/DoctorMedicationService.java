package com.graduationProject.medicory.service.doctor;

import com.graduationProject.medicory.model.medication.MedicationDTO;
import com.graduationProject.medicory.model.medication.MedicationResponseDTO;

import java.util.List;

public interface DoctorMedicationService {
    List<MedicationResponseDTO> getAllMedicationsForPatient(String userCode);
    boolean addMedicationsForPatient(String userCode, List<MedicationDTO> medications, Long prescriptionId );
    MedicationResponseDTO findMedicationById(Long medicationId);
    boolean updateMedication(Long medicationId, MedicationDTO medicationDTO);
    boolean deleteMedication(Long medicationId);
}
