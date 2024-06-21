package com.graduationProject.medicory.service.pharmacy.impl;

import com.graduationProject.medicory.entity.medicationEntities.*;
import com.graduationProject.medicory.entity.usersEntities.Owner;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.time.LocalDateTime;
import java.util.List;

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

        Owner owner = fetchOwner(userCode);
        CurrentSchedule currentSchedule = fetchOrCreateCurrentSchedule(owner);

        Medication medication = fetchMedication( currentScheduleRequest.getId());
        updateAndSaveMedication(medication,currentScheduleRequest,currentSchedule);

        return "The medication added successfully to the current schedule.";
    }


    @Override
    public List<MedicationDTO> getMedicationOfPrescription(String userCode, Long prescriptionId) {
        Prescription prescription = prescriptionRepository.findPrescriptionByUserCodeAndPrescriptionId(userCode,prescriptionId).orElseThrow(
                ()->new IllegalArgumentException("user code or prescription id is wrong.")
        );
        List<MedicationDTO> medicationsResponse = prescription.getMedications()
                .stream()
                .map(medication -> medicationMapper.toDTO(medication))
                .toList();
        return medicationsResponse;
    }

    @Override
    public String createVoiceRecord(String userCode, MultipartFile file, Long medicationId) throws IOException {
        Medication medication = fetchMedication(medicationId);
        String path = FileStorageUtil.uploadFile(file,UPLOAD_DIR,MAX_FILE_SIZE);

        VoiceRecord voiceRecord = VoiceRecord.builder()
                .name(file.getOriginalFilename())
                .path(path)
                .medication(medication)
                .createdAt(LocalDateTime.now())
                .build();

        voiceRecord.setMedication(medication);
        voiceRecordRepository.save(voiceRecord);

        return "The record created successfully.";
    }

    @Override
    public String deleteVoiceRecord(String userCode, Long medicationId, Long recordId) throws IOException {
        VoiceRecord voiceRecord = voiceRecordRepository.findByUserCodeAndMedicationIdAndRecordId(userCode,medicationId,recordId).orElseThrow(
                ()->new VoiceRecordNotFoundException("User code ,medication id or voice record id is wrong.")
        );

        String voiceRecordPath = voiceRecord.getPath();
        FileStorageUtil.deleteFile(voiceRecordPath);

        voiceRecordRepository.deleteById(recordId);

        return "The Voice record deleted successfully.";
    }


    private Owner fetchOwner(String userCode){
        return ownerRepository.findByUserCode(userCode).orElseThrow(
                ()->new IllegalArgumentException("user not fount!")
        );
    }
    private Medication fetchMedication(Long medicationId) {
        return medicationRepository.findById(medicationId).orElseThrow(
                ()->new IllegalArgumentException("medication not found!")
        );
    }
    private CurrentSchedule fetchOrCreateCurrentSchedule(Owner owner) {

        return currentScheduleRepository.findByOwnerId(owner.getId())
                .orElseGet(
                        ()->createAndSaveCurrentSchedule(owner)
                );
    }

    private CurrentSchedule createAndSaveCurrentSchedule(Owner owner) {
        CurrentSchedule currentSchedule = CurrentSchedule.builder()
                .updatedAt(LocalDateTime.now())
                .owner(owner)
                .build();

        currentScheduleRepository.save(currentSchedule);

        owner.setCurrentSchedule(currentSchedule);
        ownerRepository.save(owner);

        return currentSchedule;
    }
    private void updateAndSaveMedication(Medication medication, CurrentScheduleRequest currentScheduleRequest, CurrentSchedule currentSchedule) {
        medication.setUpdatedAt(LocalDateTime.now());

        Medicine medicine = medication.getMedicine();
        medicine.setName(currentScheduleRequest.getMedicineName());
        medicine.setSideEffects(currentScheduleRequest.getSideEffects());

        medication.setMedicine(medicine);
        medication.setDose(currentScheduleRequest.getDose());
        medication.setFrequency(currentScheduleRequest.getFrequency());
        medication.setTips(currentScheduleRequest.getTips());

        medication.setCurrentSchedule(currentSchedule);
        medicationRepository.save(medication);
    }

}
