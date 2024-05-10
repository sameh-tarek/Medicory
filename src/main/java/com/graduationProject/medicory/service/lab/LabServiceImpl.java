package com.graduationProject.medicory.service.lab;

import com.graduationProject.medicory.entity.medicationEntities.Prescription;
import com.graduationProject.medicory.mapper.medicationsMappers.PrescriptionMapper;
import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
import com.graduationProject.medicory.repository.MedicationsRepositories.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LabServiceImpl implements LabService{
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMapper prescriptionMapper;
    @Override
    public List<PrescriptionResponse> getAllPrescriptionsNeedLab(String userCode) {
        List<Prescription> prescriptions = prescriptionRepository.findAllByUserCodeLabNeededSortedByUpdatedAt(userCode);
        List<PrescriptionResponse> response = prescriptions.stream().map(
                prescription -> prescriptionMapper.toResponse(prescription)
        ).toList();
        return response;
    }

    @Override
    public List<PrescriptionResponse> getActivePrescriptionsNeedLab(String userCode) {
        List<Prescription> prescriptions = prescriptionRepository.findAllActiveByUserCodeLabNeededSortedByUpdatedAt(userCode);
        List<PrescriptionResponse> response = prescriptions.stream().map(
                prescription -> prescriptionMapper.toResponse(prescription)
        ).toList();
        return response;
    }
}
