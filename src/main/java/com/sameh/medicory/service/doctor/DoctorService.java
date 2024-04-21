package com.sameh.medicory.service.doctor;

import com.sameh.medicory.model.immunization.ImmunizationRequestDTO;
import com.sameh.medicory.model.immunization.ImmunizationResponseDTO;
import com.sameh.medicory.model.allergies.AllergiesRequestDTO;
import com.sameh.medicory.model.allergies.AllergiesResponseDTO;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesRequestDTO;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesResponseDTO;
import com.sameh.medicory.model.prescription.PrescriptionRequestDTO;
import com.sameh.medicory.model.surgery.SurgeryRequestDTO;
import com.sameh.medicory.model.surgery.SurgeryResponseDTO;
import com.sameh.medicory.model.tests.LabTestDTO;
import com.sameh.medicory.model.patient.PatientPersonalInformation;

import java.util.List;

public interface DoctorService {
    PatientPersonalInformation getPatientPersonalInformation(Long ownerId);



    List<ChronicDiseasesResponseDTO> getPatientChronicDiseases(Long ownerId);
    String addNewChronicDiseasesForPatient(ChronicDiseasesRequestDTO chronicDiseasesRequestDTO, Long ownerId);
    ChronicDiseasesResponseDTO findChronicDiseasesById(Long diseasesId);
    String updateChronicDisease(ChronicDiseasesRequestDTO chronicDiseasesRequestDTO, Long diseasesId, Long ownerId);
    String deleteChronicDiseases(Long diseasesId);



    List<AllergiesResponseDTO> getPatientAllergies(Long ownerId);
    String addNewAllergiesForPatient(AllergiesRequestDTO allergiesRequestDTO, Long ownerId);
    AllergiesResponseDTO findAllergiesById(Long allergiesId);
    String updateAllergies(AllergiesRequestDTO allergiesRequestDTO, Long allergiesId, Long ownerId);
    String deleteAllergies(Long allergiesId);



    List<ImmunizationResponseDTO> getaAllPatientImmunizations(Long ownerId);
    String addNewImmunizationForPatient(ImmunizationRequestDTO immunizationRequestDTO, Long ownerId);
    ImmunizationResponseDTO findImmunizationById(Long immunizationId);
    String updateImmunization(ImmunizationRequestDTO allergiesRequestDTO, Long immunizationId, Long ownerId);
    String deleteImmunization(Long immunizationId);




    List<SurgeryResponseDTO> getPatientSurgicalHistory(Long ownerId);
    String addNewSurgeryForPatient(SurgeryRequestDTO surgeryRequestDTO, Long ownerId);
    SurgeryResponseDTO findSurgeryById(Long surgeryId);
    String updateSurgery(SurgeryRequestDTO surgeryRequestDTO, Long surgeryId, Long ownerId);
    String deleteSurgery(Long surgeryId);


    List<LabTestDTO> findAllLabTestsForPatient(Long ownerId);
    String deleteLabTestFromHistory(Long testId);

    boolean addNewPrescription(Long ownerId, PrescriptionRequestDTO prescriptionRequestDTO);
}
