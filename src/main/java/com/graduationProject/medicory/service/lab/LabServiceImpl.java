package com.graduationProject.medicory.service.lab;

import com.graduationProject.medicory.entity.medicationEntities.Prescription;
import com.graduationProject.medicory.mapper.medicationsMappers.PrescriptionMapper;
import com.graduationProject.medicory.model.prescription.PrescriptionResponseDTO;
import com.graduationProject.medicory.repository.MedicationsRepositories.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LabServiceImpl implements LabService {

    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMapper prescriptionMapper;

    @Override
    public List<PrescriptionResponseDTO> getAllPrescriptionsNeedLab(String userCode) {
        List<Prescription> prescriptions = prescriptionRepository.findAllByUserCodeLabNeededSortedByUpdatedAt(userCode);
        return prescriptions.stream()
                .map(prescriptionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionResponseDTO> getActivePrescriptionsNeedLab(String userCode) {
        List<Prescription> prescriptions = prescriptionRepository.findAllActiveByUserCodeLabNeededSortedByUpdatedAt(userCode);
        return prescriptions.stream()
                .map(prescriptionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
