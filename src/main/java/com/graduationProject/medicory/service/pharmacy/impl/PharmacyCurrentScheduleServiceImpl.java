package com.graduationProject.medicory.service.pharmacy.impl;

import com.graduationProject.medicory.entity.medicationEntities.CurrentSchedule;
import com.graduationProject.medicory.entity.medicationEntities.Medication;
import com.graduationProject.medicory.entity.medicationEntities.Prescription;
import com.graduationProject.medicory.entity.usersEntities.Owner;
import com.graduationProject.medicory.mapper.MedicationMapper;
import com.graduationProject.medicory.model.medication.CurrentScheduleRequest;
import com.graduationProject.medicory.model.medication.MedicationDTO;
import com.graduationProject.medicory.repository.*;
import com.graduationProject.medicory.service.pharmacy.PharmacyCurrentScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    @Override
    public String createTreatmentSchedule(String userCode, CurrentScheduleRequest currentScheduleRequest) {

        Owner owner = fetchOwner(userCode);
        CurrentSchedule currentSchedule = fetchOrCreateCurrentSchedule(owner);


        Medication medication = medicationRepository.findById(currentScheduleRequest.getId()).orElseThrow(
                ()->new IllegalArgumentException("medication not found!")
        );
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

//    @Override
//    public String createVoiceRecord(MultipartFile file, Long medicationId) {
//        Medication medication = medicationRepository.findById(medicationId).orElseThrow(
//                ()->new IllegalArgumentException("Medication id is wrong.")
//        );
//
//        File destnation = new File("voice-records");
//
//        VoiceRecord voiceRecord = VoiceRecord.builder()
//                .name(file.getOriginalFilename())
//                .path(destnation.getPath()+file.getOriginalFilename())
//                .medication(medication)
//                .build();
//
//        voiceRecordRepository.save(voiceRecord);
//        return "voice record saved successfully.";
//    }

    private Owner fetchOwner(String userCode){
        return ownerRepository.findByUserCode(userCode).orElseThrow(
                ()->new IllegalArgumentException("user not fount!")
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
        medication.setDose(currentScheduleRequest.getDose());
        medication.setFrequency(currentScheduleRequest.getFrequency());
        medication.setTips(currentScheduleRequest.getTips());

        medication.setCurrentSchedule(currentSchedule);
        medicationRepository.save(medication);
    }

}
