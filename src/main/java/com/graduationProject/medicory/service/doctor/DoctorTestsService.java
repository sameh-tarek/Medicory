package com.graduationProject.medicory.service.doctor;

import com.graduationProject.medicory.model.tests.ImagingTestRequestDTO;
import com.graduationProject.medicory.model.tests.ImagingTestResponseDTO;
import com.graduationProject.medicory.model.tests.LabTestRequestDTO;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;

import java.util.List;

public interface DoctorTestsService {
    List<LabTestResponseDTO> findAllLabTestsForPatient(String userCode);
    String deleteLabTestFromHistory(Long testId);
    boolean updateLabTest(Long testId, LabTestRequestDTO labTestRequestDTO);
    boolean addLabTestsForPatientThatRequiredNow(String userCode, List<LabTestRequestDTO> requiredTests);
    List<LabTestResponseDTO> getActiveLabTests(String userCode);
    LabTestResponseDTO findLabTestById(Long testId);




    List<ImagingTestResponseDTO> getAllImagingTestForPatient(String userCode);
    boolean deleteImagingTestFromHistory(Long testId);
    boolean updateImagingTest(Long testId, ImagingTestRequestDTO imagingTestRequestDTO);
    boolean addImagingTestForPatientThatRequiredNow(String userCode, List<ImagingTestRequestDTO> requiredTests);
    List<ImagingTestResponseDTO> getActiveImagingTest(String userCode);
    ImagingTestResponseDTO findImagingTestById(Long testId);
}
