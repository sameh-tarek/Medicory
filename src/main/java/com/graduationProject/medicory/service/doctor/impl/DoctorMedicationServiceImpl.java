package com.graduationProject.medicory.service.doctor.impl;

import com.graduationProject.medicory.entity.medicationEntities.Medication;
import com.graduationProject.medicory.entity.medicationEntities.Medicine;
import com.graduationProject.medicory.entity.medicationEntities.Prescription;
import com.graduationProject.medicory.entity.usersEntities.Owner;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.mapper.medicationsMappers.MedicationMapper;
import com.graduationProject.medicory.model.medication.MedicationDTO;
import com.graduationProject.medicory.model.medication.MedicationResponseDTO;
import com.graduationProject.medicory.repository.MedicationsRepositories.MedicationRepository;
import com.graduationProject.medicory.repository.MedicationsRepositories.MedicineRepository;
import com.graduationProject.medicory.repository.MedicationsRepositories.PrescriptionRepository;
import com.graduationProject.medicory.repository.usersRepositories.OwnerRepository;
import com.graduationProject.medicory.service.doctor.DoctorMedicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorMedicationServiceImpl implements DoctorMedicationService {

    private final MedicationRepository medicationRepository;
    private final MedicationMapper medicationMapper;
    private final OwnerRepository ownerRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final MedicineRepository medicineRepository;

    @Override
    public List<MedicationResponseDTO> getAllMedicationsForPatient(String userCode) {
        Owner owner = ownerRepository.findByUserCode(userCode)
                .orElseThrow(() -> new RecordNotFoundException("Owner not found with user code: " + userCode));
        List<Medication> medications = medicationRepository.findByOwner(owner);
        return medications.stream()
                .map(medicationMapper::toDTo)
                .collect(Collectors.toList());
    }

    @Override
    public boolean addMedicationsForPatient(String userCode, List<MedicationDTO> medications, Long prescriptionId) {
        Owner owner = ownerRepository.findByUserCode(userCode)
                .orElseThrow(() -> new RecordNotFoundException("Owner not found with user code: " + userCode));
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new RecordNotFoundException("Active prescription not found for user code: " + userCode));

        List<Medication> medicationEntities = mapMedications(medications, owner, prescription);

        medicationRepository.saveAll(medicationEntities);
        return true;
    }

    private List<Medication> mapMedications(List<MedicationDTO> medicationDTOs, Owner owner, Prescription prescription) {
        return medicationDTOs.stream()
                .map(medicationRequestDTO -> {
                    Medicine medicine = getMedicineByName(medicationRequestDTO.getMedicineName());
                    Medication medication = medicationMapper.toEntity(medicationRequestDTO);
                    medication.setPrescription(prescription);
                    medication.setCreatedAt(LocalDateTime.now());
                    medication.setUpdatedAt(LocalDateTime.now());
                    medication.setOwner(owner);
                    medication.setMedicine(medicine);
                    log.info("Medication ... {}", medication);
                    return medication;
                })
                .collect(Collectors.toList());
    }

    private Medicine getMedicineByName(String name){
        Medicine medicine = medicineRepository.findByName(name)
                .orElse(null);

        if (medicine == null){
            Medicine newMedicine = new Medicine();
            newMedicine.setName(name);
            medicineRepository.save(newMedicine);
            return newMedicine;
        }

        log.info("This medicine info: {}", medicine);
        return medicine;
    }

    @Override
    public MedicationResponseDTO findMedicationById(Long medicationId) {
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new RecordNotFoundException("Medication not found with id: " + medicationId));
        return medicationMapper.toDTo(medication);
    }

    @Override
    @Transactional
    public boolean updateMedication(Long medicationId, MedicationDTO medicationDTO) {
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new RecordNotFoundException("Medication not found with id: " + medicationId));

        medication.setDose(medicationDTO.getDose());
        medication.setFrequency(medicationDTO.getFrequency());
        medication.setTips(medicationDTO.getTips());
        medicationRepository.save(medication);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteMedication(Long medicationId) {
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new RecordNotFoundException("Medication not found with id: " + medicationId));
        medicationRepository.delete(medication);
        return true;
    }
}
