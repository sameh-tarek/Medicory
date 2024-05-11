package com.graduationProject.medicory.service.lab.test;

import com.graduationProject.medicory.entity.testsEntities.LabTest;
import com.graduationProject.medicory.mapper.testsMappers.LabTestMapper;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import com.graduationProject.medicory.repository.testsRepositories.LabTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LabTestServiceImpl implements LabTestService {

    private final LabTestRepository labTestRepository;
    private final LabTestMapper labTestMapper;

    @Override
    public List<LabTestResponseDTO> getAllTestsOfPrescription(Long prescriptionId) {
        List<LabTest> tests = labTestRepository.findByPrescriptionId(prescriptionId);
        List<LabTestResponseDTO> response = tests.stream()
                .map(labTest -> labTestMapper.toDTO(labTest))
                .toList();
        return response;
    }

    @Override
    public List<LabTestResponseDTO> getActiveTestsOfPrescription(Long prescriptionId) {
        List<LabTest> tests = labTestRepository.findActiveTestsByPrescriptionId(prescriptionId);
        List<LabTestResponseDTO> response = tests.stream()
                .map(labTest -> labTestMapper.toDTO(labTest))
                .toList();
        return response;
    }

}
