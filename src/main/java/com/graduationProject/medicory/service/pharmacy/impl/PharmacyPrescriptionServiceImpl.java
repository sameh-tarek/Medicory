package com.graduationProject.medicory.service.pharmacy.impl;

import com.graduationProject.medicory.entity.medicationEntities.Prescription;
import com.graduationProject.medicory.mapper.medicationsMappers.PrescriptionMapper;
import com.graduationProject.medicory.model.prescription.PrescriptionResponseDTO;
import com.graduationProject.medicory.repository.MedicationsRepositories.PrescriptionRepository;
import com.graduationProject.medicory.service.pharmacy.PharmacyPrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PharmacyPrescriptionServiceImpl implements PharmacyPrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMapper prescriptionMapper;

    @Override
    public List<PrescriptionResponseDTO> getAllPrescription(String userCode) {
        List<Prescription> allPrescriptions = prescriptionRepository.findAllByUserCodePharmacyNeededSortedByUpdatedAt(userCode);
        return allPrescriptions.stream()
                .map(prescriptionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionResponseDTO> getActivePrescription(String userCode) {
        List<Prescription> allPrescriptions = prescriptionRepository.findAllActiveByOwnerIdPharmacyNeededSortedByUpdatedAt(userCode);
        return allPrescriptions.stream()
                .map(prescriptionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PrescriptionResponseDTO getPrescriptionById(String userCode, Long prescriptionId) {
        Prescription prescription = prescriptionRepository.findPrescriptionByUserCodeAndPrescriptionId(userCode, prescriptionId)
                .orElseThrow(() -> new IllegalArgumentException("Prescription id is wrong!"));
        return prescriptionMapper.toResponseDTO(prescription);
    }
}
