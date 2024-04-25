package com.sameh.medicory.service.doctor;

import com.sameh.medicory.model.immunization.ImmunizationRequestDTO;
import com.sameh.medicory.model.immunization.ImmunizationResponseDTO;
import com.sameh.medicory.model.allergies.AllergiesRequestDTO;
import com.sameh.medicory.model.allergies.AllergiesResponseDTO;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesRequestDTO;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesResponseDTO;
import com.sameh.medicory.model.prescription.PrescriptionRequestDTO;
import com.sameh.medicory.model.prescription.PrescriptionResponseDTO;
import com.sameh.medicory.model.surgery.SurgeryRequestDTO;
import com.sameh.medicory.model.surgery.SurgeryResponseDTO;
import com.sameh.medicory.model.tests.ImagingTestRequestDTO;
import com.sameh.medicory.model.tests.ImagingTestResponseDTO;
import com.sameh.medicory.model.tests.LabTestRequestDTO;
import com.sameh.medicory.model.tests.LabTestResponseDTO;
import com.sameh.medicory.model.patient.PatientPersonalInformation;

import java.util.List;

public interface DoctorService {
    PatientPersonalInformation getPatientPersonalInformation(String userCode);



    List<ChronicDiseasesResponseDTO> getPatientChronicDiseases(String userCode);
    String addNewChronicDiseasesForPatient(ChronicDiseasesRequestDTO chronicDiseasesRequestDTO, String userCode);
    ChronicDiseasesResponseDTO findChronicDiseasesById(Long diseasesId);
    String updateChronicDisease(ChronicDiseasesRequestDTO chronicDiseasesRequestDTO, Long diseasesId, String userCode);
    String deleteChronicDiseases(Long diseasesId);



    List<AllergiesResponseDTO> getPatientAllergies(String userCode);
    String addNewAllergiesForPatient(AllergiesRequestDTO allergiesRequestDTO, String userCode);
    AllergiesResponseDTO findAllergiesById(Long allergiesId);
    String updateAllergies(AllergiesRequestDTO allergiesRequestDTO, Long allergiesId, String userCode);
    String deleteAllergies(Long allergiesId);



    List<ImmunizationResponseDTO> getaAllPatientImmunizations(String userCode);
    String addNewImmunizationForPatient(ImmunizationRequestDTO immunizationRequestDTO, String userCode);
    ImmunizationResponseDTO findImmunizationById(Long immunizationId);
    String updateImmunization(ImmunizationRequestDTO allergiesRequestDTO, Long immunizationId, String userCode);
    String deleteImmunization(Long immunizationId);




    List<SurgeryResponseDTO> getPatientSurgicalHistory(String userCode);
    String addNewSurgeryForPatient(SurgeryRequestDTO surgeryRequestDTO, String userCode);
    SurgeryResponseDTO findSurgeryById(Long surgeryId);
    String updateSurgery(SurgeryRequestDTO surgeryRequestDTO, Long surgeryId, String userCode);
    String deleteSurgery(Long surgeryId);




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



    boolean addNewPrescription(String userCode, PrescriptionRequestDTO prescriptionRequestDTO);
    PrescriptionResponseDTO findPrescriptionById(Long prescriptionId);
    List<PrescriptionResponseDTO> getAllPrescriptions(String userCode);

}
