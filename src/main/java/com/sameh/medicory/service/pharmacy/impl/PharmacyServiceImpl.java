package com.sameh.medicory.service.pharmacy.impl;

import com.sameh.medicory.entity.medicationEntities.CurrentSchedule;
import com.sameh.medicory.entity.medicationEntities.Prescription;
import com.sameh.medicory.mapper.CurrentScheduleMapper;
import com.sameh.medicory.mapper.MedicationScheduleMapper;
import com.sameh.medicory.mapper.PrescriptionMaper;
import com.sameh.medicory.model.medication.CurrentScheduleRequest;
import com.sameh.medicory.model.medication.MedicationRequestDTO;
import com.sameh.medicory.model.prescription.PrescriptionResponse;
import com.sameh.medicory.repository.CurrentScheduleRepository;
import com.sameh.medicory.repository.PrescriptionRepository;
import com.sameh.medicory.service.pharmacy.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PharmacyServiceImpl implements PharmacyService {
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMaper prescriptionMaper;
    private final CurrentScheduleRepository currentScheduleRepository;
    private final CurrentScheduleMapper currentScheduleMapper;
    @Override
    public List<PrescriptionResponse> getAllPrescription(Long ownerId) {
        List<Prescription> allPrescriptions = prescriptionRepository.findAllByOwnerIdSortedByUpdatedAt(ownerId);

        List<PrescriptionResponse> response = allPrescriptions.stream()
                .map(prescription -> prescriptionMaper.toResponse(prescription))
                .toList();

        return response;
    }
    @Override
    public List<PrescriptionResponse> getActivePrescription(Long ownerId) {
        List<Prescription> allPrescriptions = prescriptionRepository.findAllActiveByOwnerIdSortedByUpdatedAt(ownerId);

        List<PrescriptionResponse> response = allPrescriptions.stream()
                .map(prescription -> prescriptionMaper.toResponse(prescription))
                .toList();

        return response;
    }

    @Override
    public PrescriptionResponse getPrescriptionById(Long id) {

        Prescription prescription = prescriptionRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("Prescription id is wrong!")
        );
        PrescriptionResponse response = prescriptionMaper.toResponse(prescription);
        return response;
    }

    @Override
    public String createTreatmentSchedule(CurrentScheduleRequest currentScheduleRequest) {
        CurrentSchedule currentSchedule = currentScheduleMapper.toEntity(currentScheduleRequest);
        currentScheduleRepository.save(currentSchedule);
        return "The treatment schedule created successfully.";
    }
}
