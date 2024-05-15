package com.graduationProject.medicory.service.lab.test;

import com.graduationProject.medicory.entity.testsEntities.LabTest;
import com.graduationProject.medicory.exception.RecordNotFoundException;
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

    @Value("${application.file_storage.upload_dir.tests}")
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
        createDirectoryIfNotExist(UPLOAD_DIR);
        saveFile(path, file);
        updateTestWithTheResult(path,testId);

        return "file uploaded successfully";
    }

    @Override
    public String deleteTestResult(Long testId) throws IOException {
        LabTest test = labTestRepository.findById(testId).orElseThrow(
                ()->new IllegalArgumentException("test not found!")
        );
        String path = test.getTestResultPath();
        deleteTestResultFromStorage(path);
        test.setStatus(true);
        test.setTestResultPath(null);
        labTestRepository.save(test);

        return "The result of the test deleted successfully.";
    }

    private void deleteTestResultFromStorage(String filePath) throws IOException {
        if(filePath!=null){
            Path path = Paths.get(filePath);
            Files.deleteIfExists(path);
        }
    }

    private void createDirectoryIfNotExist(String uploadDir) throws IOException {
        Path directory = Paths.get(uploadDir);
        if(!Files.exists(directory)){
            Files.createDirectories(directory);
        }
    }

    private void updateTestWithTheResult(String filePath, Long testId) {
        LabTest labTest = labTestRepository.findById(testId).orElseThrow(
                ()->new RecordNotFoundException("This test isn't exist.")
        );
        labTest.setStatus(false);
        labTest.setTestResultPath(filePath);
        labTestRepository.save(labTest);
    }

    private void saveFile(String filePath, MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(filePath);
        Files.write(path, bytes);
    }
}

