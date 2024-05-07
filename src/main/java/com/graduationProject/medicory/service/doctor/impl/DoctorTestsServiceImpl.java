package com.graduationProject.medicory.service.doctor.impl;

import com.graduationProject.medicory.entity.testsEntities.ImagingTest;
import com.graduationProject.medicory.entity.testsEntities.LabTest;
import com.graduationProject.medicory.entity.usersEntities.Owner;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.mapper.ImagingTestMapper;
import com.graduationProject.medicory.mapper.LabTestMapper;
import com.graduationProject.medicory.model.tests.ImagingTestRequestDTO;
import com.graduationProject.medicory.model.tests.ImagingTestResponseDTO;
import com.graduationProject.medicory.model.tests.LabTestRequestDTO;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import com.graduationProject.medicory.repository.ImagingTestRepository;
import com.graduationProject.medicory.repository.LabTestRepository;
import com.graduationProject.medicory.repository.OwnerRepository;
import com.graduationProject.medicory.service.doctor.DoctorTestsService;
import com.graduationProject.medicory.utils.OwnerContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorTestsServiceImpl implements DoctorTestsService {

    private final LabTestMapper labTestMapper;
    private final LabTestRepository labTestRepository;
    private final ImagingTestMapper imagingTestMapper;
    private final ImagingTestRepository imagingTestRepository;
    private final OwnerContext ownerContext;
    private final OwnerRepository ownerRepository;

    @Override
    public List<LabTestResponseDTO> findAllLabTestsForPatient(String userCode) {
        Owner patientOwner = ownerContext.getCurrentOwner(userCode);
        log.info("Doctor want to get all Lab Tests for owner with id {}", patientOwner.getId());
        List<LabTestResponseDTO> labTestResponseDTOS = patientOwner.getLabTests()
                .stream()
                .map(labTestMapper::toDTO)
                .collect(Collectors.toList());
        log.info("Patient Lab Tests {}", labTestResponseDTOS);
        return labTestResponseDTOS;
    }

    @Override
    public String deleteLabTestFromHistory(Long testId) {
        LabTest labTest = labTestRepository.findById(testId)
                .orElseThrow(() -> new RecordNotFoundException("This Lab test Already doesn't exists"));
        labTestRepository.delete(labTest);
        return "deleted successfully";
    }

    @Override
    public boolean updateLabTest(Long testId, LabTestRequestDTO labTestRequestDTO) {
        log.trace("Doctor want to update lab test with id {}, this new update {}", testId, labTestRequestDTO);
        LabTest existTest = labTestRepository.findById(testId)
                .orElseThrow(() -> new RecordNotFoundException("This test with id " + testId + " doesn't exist!"));

        LabTest updatedLabTest = labTestMapper.toEntity(labTestRequestDTO);
        updatedLabTest.setCreatedAt(existTest.getCreatedAt());
        updatedLabTest.setUpdatedAt(LocalDateTime.now());
        updatedLabTest.setId(existTest.getId());
        updatedLabTest.setOwner(existTest.getOwner());
        labTestRepository.save(updatedLabTest);
        return true;
    }


    @Override
    public boolean addLabTestsForPatientThatRequiredNow(String userCode, List<LabTestRequestDTO> requiredTests) {
        log.info("Doctor need to ask patient with id {}, for this lab tests {}", userCode, requiredTests);

        Owner owner = ownerContext.getCurrentOwner(userCode);
        List<LabTest> labTests = requiredTests.stream()
                .map(labTestMapper::toEntity)
                .map(labTest -> {
                    labTest.setStatus(true);
                    labTest.setOwner(owner);
                    return labTest;
                })
                .collect(Collectors.toList());

        owner.getLabTests().addAll(labTests);
        ownerRepository.save(owner);
        log.info("lab tests after add {}", owner.getLabTests());
        return true;
    }

    @Override
    public List<LabTestResponseDTO> getActiveLabTests(String userCode) {
        Owner patientOwner = ownerContext.getCurrentOwner(userCode);
        log.info("Doctor want to get Active Lab Tests for owner with id {}", patientOwner.getId());
        List<LabTestResponseDTO> labTestResponseDTOS = patientOwner.getLabTests().stream()
                .filter(labTest -> labTest.isStatus())
                .map(labTestMapper::toDTO)
                .collect(Collectors.toList());
        log.info("All active lab tests {}", labTestResponseDTOS);
        return labTestResponseDTOS;
    }

    @Override
    public LabTestResponseDTO findLabTestById(Long testId) {
        LabTest labTest = labTestRepository.findById(testId)
                .orElseThrow(() -> new RecordNotFoundException("This lab test with id " + testId + " doesn't exist!"));
        log.info("lab test with id {} is {}", testId, labTest);
        return labTestMapper.toDTO(labTest);
    }


    @Override
    public List<ImagingTestResponseDTO> getAllImagingTestForPatient(String userCode) {
        Owner patientOwner = ownerContext.getCurrentOwner(userCode);
        log.info("Doctor want to get all Lab Tests for owner with id {}", patientOwner.getId());
        List<ImagingTestResponseDTO> imagingTestResponseDTOS = patientOwner.getImagingTests()
                .stream()
                .map(imagingTestMapper::toDTO)
                .collect(Collectors.toList());
        log.info("Patient Lab Tests {}", imagingTestResponseDTOS);
        return imagingTestResponseDTOS;
    }

    @Override
    public boolean deleteImagingTestFromHistory(Long testId) {
        ImagingTest imagingTest = imagingTestRepository.findById(testId)
                .orElseThrow(() -> new RecordNotFoundException("This Imaging Test with id " + testId + " doesn't exist!"));
        imagingTestRepository.delete(imagingTest);
        return true;
    }

    @Override
    public boolean updateImagingTest(Long testId, ImagingTestRequestDTO imagingTestRequestDTO) {
        log.trace("Doctor want to update Imaging test with id {}, this new update {}", testId, imagingTestRequestDTO);
        ImagingTest existTest = imagingTestRepository.findById(testId)
                .orElseThrow(() -> new RecordNotFoundException("This Imaging Test with id " + testId + " doesn't exist!"));

        ImagingTest updatedImagingTest = imagingTestMapper.toEntity(imagingTestRequestDTO);
        updatedImagingTest.setCreatedAt(existTest.getCreatedAt());
        updatedImagingTest.setUpdatedAt(LocalDateTime.now());
        updatedImagingTest.setId(existTest.getId());
        updatedImagingTest.setOwner(existTest.getOwner());
        imagingTestRepository.save(updatedImagingTest);
        return true;
    }

    @Override
    public boolean addImagingTestForPatientThatRequiredNow(String userCode, List<ImagingTestRequestDTO> requiredTests) {
        log.info("Doctor need to ask patient with id {}, for this Imaging tests {}", userCode, requiredTests);

        Owner owner = ownerContext.getCurrentOwner(userCode);
        List<ImagingTest> imagingTests = requiredTests.stream()
                .map(imagingTestMapper::toEntity)
                .map(imagingTest -> {
                    imagingTest.setStatus(true);
                    imagingTest.setOwner(owner);
                    return imagingTest;
                })
                .collect(Collectors.toList());

        owner.getImagingTests().addAll(imagingTests);
        ownerRepository.save(owner);
        log.info("lab tests after add {}", owner.getLabTests());
        return true;
    }

    @Override
    public List<ImagingTestResponseDTO> getActiveImagingTest(String userCode) {
        Owner patientOwner = ownerContext.getCurrentOwner(userCode);
        log.info("Doctor want to get Active Lab Tests for owner with id {}", patientOwner.getId());
        List<ImagingTestResponseDTO> imagingTestResponseDTOS = patientOwner.getImagingTests().stream()
                .filter(imagingTest -> imagingTest.isStatus())
                .map(imagingTestMapper::toDTO)
                .collect(Collectors.toList());
        log.info("All active lab tests {}", imagingTestResponseDTOS);
        return imagingTestResponseDTOS;
    }

    @Override
    public ImagingTestResponseDTO findImagingTestById(Long testId) {
        ImagingTest imagingTest = imagingTestRepository.findById(testId)
                .orElseThrow(() -> new RecordNotFoundException("This imaging test with id " + testId + " doesn't exist!"));
        log.info("imaging test with id {} is {}", testId, imagingTest);
        return imagingTestMapper.toDTO(imagingTest);

    }
}
