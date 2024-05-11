package com.graduationProject.medicory.service.lab.test;

import com.graduationProject.medicory.entity.testsEntities.LabTest;
import com.graduationProject.medicory.exception.StorageException;
import com.graduationProject.medicory.mapper.testsMappers.LabTestMapper;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import com.graduationProject.medicory.repository.testsRepositories.LabTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
//
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LabTestServiceImpl implements LabTestService {

    private final LabTestRepository labTestRepository;
    private final LabTestMapper labTestMapper;

    @Value("${application.security.upload_dir}")
    private String UPLOAD_DIR;

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

    @Override
    public String uploadTestResult(MultipartFile file, Long testId) throws IOException {

        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file.");
        }
        String fileName = file.getOriginalFilename();
        String path = UPLOAD_DIR + fileName;

        saveFile(path, file);
        return "file uploaded successfully";
    }

    private void saveFile(String filePath, MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(filePath);
//        Path path = Paths.get(filePath).normalize().toAbsolutePath();
//
//        if(path.getParent().equals(this.UPLOAD_DIR))

        Files.write(path, bytes);
    }
}

