package com.graduationProject.medicory.service.lab.test;

import com.graduationProject.medicory.entity.medicationEntities.Prescription;
import com.graduationProject.medicory.entity.testsEntities.LabTest;
import com.graduationProject.medicory.mapper.LabTestMapper;
import com.graduationProject.medicory.mapper.PrescriptionMaper;
import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import com.graduationProject.medicory.repository.LabTestRepository;
import com.graduationProject.medicory.repository.OwnerRepository;
import com.graduationProject.medicory.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LabTestServiceImpl implements LabTestService {
    private final LabTestRepository labTestRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMaper prescriptionMaper;
    private final LabTestMapper labTestMapper;
    @Override
    public List<PrescriptionResponse> getAllPrescriptionsHaveTests(String userCode) {

        List<Prescription> prescriptions = prescriptionRepository.findAllByOwnerIdLabNeededSortedByUpdatedAt(userCode);

        List<PrescriptionResponse> response = prescriptions.stream().map(prescription -> prescriptionMaper.toResponse(prescription))
                .toList();

        return response;
    }

    @Override
    public List<PrescriptionResponse> getActivePrescriptionsHaveTests(String userCode) {
        List<Prescription> prescriptions = prescriptionRepository.findAllActiveByOwnerIdLabNeededSortedByUpdatedAt(userCode);

        List<PrescriptionResponse> response = prescriptions.stream()
                .map(prescription -> prescriptionMaper.toResponse(prescription))
                .toList();

        return response;
    }

    @Override
    public List<LabTestResponseDTO> getAllTestsOfPrescription(Long prescriptionId) {
        List<LabTest> tests = labTestRepository.findByPrescriptionId(prescriptionId);
        List<LabTestResponseDTO> response = tests.stream()
                .map(labTest -> labTestMapper.toDTO(labTest))
                .toList();
        return response;
    }

    @Override
    public List<LabTestResponseDTO> getActiveTests(Long prescriptionId) {
        List<LabTest> tests = labTestRepository.findActiveTestsByPrescriptionId(prescriptionId);
        List<LabTestResponseDTO> response = tests.stream()
                .map(labTest -> labTestMapper.toDTO(labTest))
                .toList();
        return response;
    }

}
