package com.graduationProject.medicory.service.lab.imageTest;


import com.graduationProject.medicory.entity.testsEntities.ImagingTest;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.exception.StorageException;
import com.graduationProject.medicory.mapper.testsMappers.ImagingTestMapper;
import com.graduationProject.medicory.model.tests.ImagingTestResponseDTO;
import com.graduationProject.medicory.repository.testsRepositories.ImagingTestRepository;
import com.graduationProject.medicory.utils.FileStorageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@RequiredArgsConstructor
@Service
public class LabImageTestServiceImpl implements LabImageTestService {

    private final ImagingTestRepository imagingTestRepository;
    private final ImagingTestMapper imagingTestMapper;

    @Value("${application.file_storage.lab.image-tests}")
    private String UPLOAD_DIR;

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

    @Override
    public String uploadImageTestResult(MultipartFile file, Long imageTestId) throws IOException {

        if(file.isEmpty()){
            throw new StorageException("Failed to store empty file.");
        }
        String fileName = file.getOriginalFilename();
        String path = UPLOAD_DIR+fileName;
        FileStorageUtil.createDirectoryIfNotExist(UPLOAD_DIR);
        FileStorageUtil.saveFile(path,file);
        updateTestWithTheResult(path,imageTestId);

        return "Result uploaded successfully";
    }
    @Override
    public String deleteImageTestResult(Long testId) throws IOException {
        ImagingTest imagingTest = imagingTestRepository.findById(testId).orElseThrow(
                ()->new RecordNotFoundException("Imaging test not found for id: " + testId)
        );

        String path = imagingTest.getImageResult();
        if(path!=null) {
            FileStorageUtil.deleteFile(path);
        }

        FileStorageUtil.deleteFile(path);
        imagingTest.setStatus(true);
        imagingTest.setImageResult(null);
        return "Result deleted successfully.";
    }

    private void updateTestWithTheResult(String path, Long imageTestId) {
        ImagingTest imagingTest = imagingTestRepository.findById(imageTestId).orElseThrow(
                ()->new RecordNotFoundException("This test isn't exist.")
        );
        imagingTest.setStatus(false);
        imagingTest.setImageResult(path);
        imagingTestRepository.save(imagingTest);
    }

}
