package com.graduationProject.medicory.service.lab.test;

import com.graduationProject.medicory.entity.testsEntities.LabTest;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.exception.ResutExistsException;
import com.graduationProject.medicory.mapper.testsMappers.LabTestMapper;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import com.graduationProject.medicory.repository.testsRepositories.LabTestRepository;
import com.graduationProject.medicory.utils.FileStorageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
//
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LabTestServiceImpl implements LabTestService {

    private final LabTestRepository labTestRepository;
    private final LabTestMapper labTestMapper;

    @Value("${application.file-storage.lab.tests}")
    private String UPLOAD_DIR;
    @Value("${application.file-storage.lab.file-size}")
    private long MAX_FILE_SIZE;

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

    @Override//
    public String uploadTestResult(MultipartFile file, Long testId) throws IOException {
        if(isResultExists(testId)){
            throw new ResutExistsException("The result of this test is exist you can delete it then update.");
        }
        String path = FileStorageUtil.uploadFile(file, UPLOAD_DIR, MAX_FILE_SIZE);
        updateTestWithTheResult(path, testId);
        return "Result uploaded successfully";
    }
    @Override
    public String deleteTestResult(Long testId) throws IOException {
        LabTest test = labTestRepository.findById(testId).orElseThrow(
                ()->new RecordNotFoundException("Imaging test not found for id: " + testId)
        );

        String path = test.getTestResultPath();
        FileStorageUtil.deleteFile(path);

        test.setStatus(true);
        test.setTestResultPath(null);

        labTestRepository.save(test);

        return "The result of the test deleted successfully.";
    }

    private void updateTestWithTheResult(String filePath, Long testId) {
        LabTest labTest = labTestRepository.findById(testId).orElseThrow(
                ()->new RecordNotFoundException("This test isn't exist.")
        );
        labTest.setStatus(false);
        labTest.setTestResultPath(filePath);
        labTestRepository.save(labTest);
    }
    private boolean isResultExists(Long testId) {
        LabTest labTest = labTestRepository.findById(testId).orElseThrow(
                ()->new RecordNotFoundException("This test isn't exist.")
        );
        return !labTest.isStatus();
    }
}

