package com.graduationProject.medicory.service.doctor;

import com.graduationProject.medicory.model.tests.ImagingTestRequestDTO;
import com.graduationProject.medicory.model.tests.ImagingTestResponseDTO;
import com.graduationProject.medicory.model.tests.LabTestRequestDTO;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;

import java.util.List;

public interface DoctorTestsService {
    List<LabTestResponseDTO> findAllLabTestsForPatient(String userCode);
    List<LabTestResponseDTO> getAllPrescriptionLabTestsForPatient(Long prescriptionId);
    String deleteLabTestFromHistory(Long testId);
    boolean updateLabTest(Long testId, LabTestRequestDTO labTestRequestDTO);
    boolean addPrescriptionLabTestsForPatient(String userCode, List<LabTestRequestDTO> requiredTests, Long prescriptionId);
    List<LabTestResponseDTO> getActiveLabTests(String userCode);
    LabTestResponseDTO findLabTestById(Long testId);
    boolean changeLabTestStatus(Long testId);




    List<ImagingTestResponseDTO> getAllImagingTestForPatient(String userCode);
    List<ImagingTestResponseDTO> getAllPrescriptionImagingTestForPatient(Long prescriptionId);
    boolean deleteImagingTestFromHistory(Long testId);
    boolean updateImagingTest(Long testId, ImagingTestRequestDTO imagingTestRequestDTO);
    boolean addImagingTestForPatient(String userCode, List<ImagingTestRequestDTO> requiredTests, Long prescriptionId);
    List<ImagingTestResponseDTO> getActiveImagingTest(String userCode);
    ImagingTestResponseDTO findImagingTestById(Long testId);

    boolean changeImagingTestStatus(Long testId);
}
