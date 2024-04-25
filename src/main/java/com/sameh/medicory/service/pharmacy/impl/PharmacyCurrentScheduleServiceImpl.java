package com.sameh.medicory.service.pharmacy.impl;

import com.sameh.medicory.entity.medicationEntities.CurrentSchedule;
import com.sameh.medicory.entity.medicationEntities.Medication;
import com.sameh.medicory.entity.medicationEntities.Prescription;
import com.sameh.medicory.entity.usersEntities.Owner;
import com.sameh.medicory.mapper.MedicationMapper;
import com.sameh.medicory.model.medication.CurrentScheduleRequest;
import com.sameh.medicory.model.medication.MedicationDTO;
import com.sameh.medicory.repository.CurrentScheduleRepository;
import com.sameh.medicory.repository.MedicationRepository;
import com.sameh.medicory.repository.OwnerRepository;
import com.sameh.medicory.repository.PrescriptionRepository;
import com.sameh.medicory.service.pharmacy.PharmacyCurrentScheduleService;
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

    @Override
    public String createTreatmentSchedule(String userCode, CurrentScheduleRequest currentScheduleRequest) {

        Owner owner = fetchOwner(userCode);
        CurrentSchedule currentSchedule = fetchOrCreateCurrentSchedule(owner);


        Medication medication = medicationRepository.findById(currentScheduleRequest.getId()).orElseThrow(
                ()->new IllegalArgumentException("medication not fount!")
        );
        updateAndSaveMedication(medication,currentScheduleRequest,currentSchedule);

        return "The medication added successfully to the current schedule.";
    }


    @Override
    public List<MedicationDTO> getMedicationOfPrescription(String userCode, Long prescriptionId) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId).orElseThrow(
                ()->new IllegalArgumentException("not found")
        );
        List<MedicationDTO> medicationsResponse = prescription.getMedications()
                .stream()
                .map(medication -> medicationMapper.toDTO(medication))
                .toList();
        return medicationsResponse;
    }

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
