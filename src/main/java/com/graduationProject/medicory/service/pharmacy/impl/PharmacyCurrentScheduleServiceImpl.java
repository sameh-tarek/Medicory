package com.graduationProject.medicory.service.pharmacy.impl;

import com.graduationProject.medicory.entity.medicationEntities.*;
import com.graduationProject.medicory.entity.usersEntities.Owner;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.exception.VoiceRecordNotFoundException;
import com.graduationProject.medicory.mapper.medicationsMappers.MedicationMapper;
import com.graduationProject.medicory.model.medication.CurrentScheduleRequest;
import com.graduationProject.medicory.model.medication.MedicationDTO;
import com.graduationProject.medicory.repository.MedicationsRepositories.CurrentScheduleRepository;
import com.graduationProject.medicory.repository.MedicationsRepositories.MedicationRepository;
import com.graduationProject.medicory.repository.MedicationsRepositories.PrescriptionRepository;
import com.graduationProject.medicory.repository.otherRepositories.VoiceRecordRepository;
import com.graduationProject.medicory.repository.usersRepositories.OwnerRepository;
import com.graduationProject.medicory.service.pharmacy.PharmacyCurrentScheduleService;
import com.graduationProject.medicory.utils.FileStorageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PharmacyCurrentScheduleServiceImpl implements PharmacyCurrentScheduleService {
    private final OwnerRepository ownerRepository;
    private final MedicationRepository medicationRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final MedicationMapper medicationMapper;
    private final CurrentScheduleRepository currentScheduleRepository;
    private final VoiceRecordRepository voiceRecordRepository;

    @Value("${application.file-storage.pharmacy.records}")
    private String UPLOAD_DIR;
    @Value("${application.file-storage.pharmacy.record-size}")
    private long MAX_FILE_SIZE;

    @Override
    public String createTreatmentSchedule(String userCode, CurrentScheduleRequest currentScheduleRequest) {
        log.info("Creating treatment schedule for userCode: {} with request: {}", userCode, currentScheduleRequest);
        Medication medication = fetchMedicationUsingUserCodeAndMedicationId(userCode, currentScheduleRequest.getId());


        if(isInCurrentSchedule(medication)){
            log.info("Medication ID {} is already added to current schedule.", medication.getId());
            return "The medication is already added to the current schedule.";
        }

        Owner owner = fetchOwner(medication);
        CurrentSchedule currentSchedule = fetchOrCreateCurrentSchedule(owner);

        updateAndSaveMedication(medication, currentScheduleRequest, currentSchedule);

        log.info("Treatment schedule created successfully for medication ID: {}", medication.getId());
        return "The medication added successfully to the current schedule.";
    }

    @Override
    public String deleteTreatmentFromCurrentSchedule(String userCode, Long medicationId) {
        log.info("Deleting treatment from current schedule for userCode: {}, medicationId: {}", userCode, medicationId);

        Medication medication = fetchMedicationUsingUserCodeAndMedicationId(userCode, medicationId);

        if (!isInCurrentSchedule(medication)) {
            log.info("Medication ID {} is already deleted from current schedule.", medicationId);
            return "The medication is already deleted from the current schedule.";
        }


        medication.setCurrentSchedule(null);
        medication.setMedicationStatus(false);
        medication.setUpdatedAt(LocalDateTime.now());

        medicationRepository.save(medication);

        log.info("Medication with ID {} deleted from current schedule by user {}", medicationId, userCode);
        return "The medication deleted successfully from current schedule.";
    }

    @Override
    public List<MedicationDTO> getMedicationOfPrescription(String userCode, Long prescriptionId) {
        log.info("Fetching medications for prescription ID: {} and userCode: {}", prescriptionId, userCode);
        Prescription prescription = prescriptionRepository.findPrescriptionByUserCodeAndPrescriptionId(userCode, prescriptionId)
                .orElseThrow(() -> {
                    log.error("Prescription not found for userCode: {} and prescriptionId: {}", userCode, prescriptionId);
                    return new RecordNotFoundException("User code or prescription ID is wrong.");
                });
        List<MedicationDTO> medicationsResponse = prescription.getMedications()
                .stream()
                .map(medicationMapper::toDTO)
                .toList();
        log.info("Fetched {} medications for prescription ID: {}", medicationsResponse.size(), prescriptionId);
        return medicationsResponse;
    }

    @Override
    public String createVoiceRecord(String userCode, MultipartFile file, Long medicationId) throws IOException {
        log.info("Creating voice record for userCode: {}, medicationId: {}", userCode, medicationId);
        Medication medication = fetchMedicationUsingUserCodeAndMedicationId(userCode, medicationId);
        String path = FileStorageUtil.uploadFile(file, UPLOAD_DIR, MAX_FILE_SIZE);

        VoiceRecord voiceRecord = VoiceRecord.builder()
                .name(file.getOriginalFilename())
                .path(path)
                .medication(medication)
                .createdAt(LocalDateTime.now())
                .build();

        voiceRecordRepository.save(voiceRecord);

        log.info("Voice record created successfully for medication ID: {}", medicationId);
        return "The record created successfully.";
    }

    @Override
    public String deleteVoiceRecord(String userCode, Long medicationId, Long recordId) throws IOException {
        log.info("Deleting voice record for userCode: {}, medicationId: {}, recordId: {}", userCode, medicationId, recordId);
        VoiceRecord voiceRecord = voiceRecordRepository.findByUserCodeAndMedicationIdAndRecordId(userCode, medicationId, recordId)
                .orElseThrow(() -> {
                    log.error("Voice record not found for userCode: {}, medicationId: {}, recordId: {}", userCode, medicationId, recordId);
                    return new VoiceRecordNotFoundException("User code, medication ID, or voice record ID is wrong.");
                });

        String voiceRecordPath = voiceRecord.getPath();
        FileStorageUtil.deleteFile(voiceRecordPath);

        voiceRecordRepository.deleteById(recordId);

        log.info("Voice record with ID {} deleted successfully.", recordId);
        return "The Voice record deleted successfully.";
    }

    private Medication fetchMedicationUsingUserCodeAndMedicationId(String userCode, Long id) {
        log.info("Fetching medication for userCode: {} and medicationId: {}", userCode, id);
        return medicationRepository.findByIdAndUserCode(id, userCode)
                .orElseThrow(() -> {
                    log.error("Medication not found for userCode: {} and medicationId: {}", userCode, id);
                    return new RecordNotFoundException("There is an error with the user code or medication ID");
                });
    }

    private Owner fetchOwner(Medication medication) {
        log.info("Fetching owner for medication ID: {}", medication.getId());
        return medication.getOwner();
    }
    private boolean isInCurrentSchedule(Medication medication) {
        return medication.getCurrentSchedule() != null && medication.isMedicationStatus();
    }

    private CurrentSchedule fetchOrCreateCurrentSchedule(Owner owner) {
        log.info("Fetching or creating current schedule for owner ID: {}", owner.getId());
        return currentScheduleRepository.findByOwnerId(owner.getId())
                .orElseGet(() -> {
                    log.info("Current schedule not found for owner ID: {}. Creating new schedule.", owner.getId());
                    return createAndSaveCurrentSchedule(owner);
                });
    }

    private CurrentSchedule createAndSaveCurrentSchedule(Owner owner) {
        log.info("Creating and saving current schedule for owner ID: {}", owner.getId());
        CurrentSchedule currentSchedule = CurrentSchedule.builder()
                .updatedAt(LocalDateTime.now())
                .owner(owner)
                .build();

        currentScheduleRepository.save(currentSchedule);
        owner.setCurrentSchedule(currentSchedule);
        ownerRepository.save(owner);

        log.info("Current schedule created and saved successfully for owner ID: {}", owner.getId());
        return currentSchedule;
    }

    private void updateAndSaveMedication(Medication medication, CurrentScheduleRequest currentScheduleRequest, CurrentSchedule currentSchedule) {
        log.info("Updating medication ID: {} with new schedule request.", medication.getId());
        medication.setUpdatedAt(LocalDateTime.now());

        Medicine medicine = medication.getMedicine();
        medicine.setName(currentScheduleRequest.getMedicineName());
        medicine.setSideEffects(currentScheduleRequest.getSideEffects());

        medication.setMedicine(medicine);
        medication.setDose(currentScheduleRequest.getDose());
        medication.setFrequency(currentScheduleRequest.getFrequency());
        medication.setTips(currentScheduleRequest.getTips());
        medication.setMedicationStatus(true);
        medication.setCurrentSchedule(currentSchedule);

        medicationRepository.save(medication);

        log.info("Medication ID: {} updated and saved successfully.", medication.getId());
    }
}
