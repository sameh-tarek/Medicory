package com.graduationProject.medicory.service.lab.imageTest;


import com.graduationProject.medicory.entity.testsEntities.ImagingTest;
import com.graduationProject.medicory.mapper.testsMappers.ImagingTestMapper;
import com.graduationProject.medicory.model.tests.ImagingTestResponseDTO;
import com.graduationProject.medicory.repository.testsRepositories.ImagingTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class LabImageTestServiceImpl implements LabImageTestService {

    private final ImagingTestRepository imagingTestRepository;
    private final ImagingTestMapper imagingTestMapper;

    @Override
    public List<ImagingTestResponseDTO> getAllImageTestsOfPrescription(Long prescriptionId) {
        List<ImagingTest> imagingTests = imagingTestRepository.findByPrescriptionId(prescriptionId);
        List<ImagingTestResponseDTO> response = imagingTests.stream()
                .map(imagingTest -> imagingTestMapper.toDTO(imagingTest))
                .toList();
        return response;
    }

    @Override
    public List<ImagingTestResponseDTO> getActiveImageTestsOfPrescription(Long prescriptionId) {

        List<ImagingTest> imagingTests = imagingTestRepository.findActiveTestsByPrescriptionId(prescriptionId);
        List<ImagingTestResponseDTO> response = imagingTests.stream()
                .map(imagingTest -> imagingTestMapper.toDTO(imagingTest))
                .toList();
        return response;
    }
}
