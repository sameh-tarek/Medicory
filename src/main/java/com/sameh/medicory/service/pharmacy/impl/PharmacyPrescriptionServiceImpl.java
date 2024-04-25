package com.sameh.medicory.service.pharmacy.impl;


import com.sameh.medicory.entity.medicationEntities.Prescription;
import com.sameh.medicory.mapper.CurrentScheduleMapper;
import com.sameh.medicory.mapper.PrescriptionMaper;
import com.sameh.medicory.model.prescription.PrescriptionResponse;
import com.sameh.medicory.repository.CurrentScheduleRepository;
import com.sameh.medicory.repository.PrescriptionRepository;
import com.sameh.medicory.service.pharmacy.PharmacyPrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PharmacyPrescriptionServiceImpl implements PharmacyPrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMaper prescriptionMaper;

    @Override
    public List<PrescriptionResponse> getAllPrescription(String userCode) {
        System.out.println(userCode);
        List<Prescription> allPrescriptions = prescriptionRepository.findAllByOwnerIdSortedByUpdatedAt(userCode);

        List<PrescriptionResponse> response = allPrescriptions.stream()
                .map(prescription -> prescriptionMaper.toResponse(prescription))
                .toList();

        return response;
    }
    @Override
    public List<PrescriptionResponse> getActivePrescription(String userCode) {
        List<Prescription> allPrescriptions = prescriptionRepository.findAllActiveByOwnerIdSortedByUpdatedAt(userCode);

        List<PrescriptionResponse> response = allPrescriptions.stream()
                .map(prescription -> prescriptionMaper.toResponse(prescription))
                .toList();

        return response;
    }

    @Override
    public PrescriptionResponse getPrescriptionById(String userCode, Long prescriptionId) {

        Prescription prescription = prescriptionRepository.findPrescriptionByUserCodeAndPrescriptionId(userCode,prescriptionId).orElseThrow(
                ()->new IllegalArgumentException("Prescription id is wrong!")
        );
        PrescriptionResponse response = prescriptionMaper.toResponse(prescription);
        return response;
    }


}
