package com.graduationProject.medicory.service.doctor;

import com.graduationProject.medicory.model.immunization.ImmunizationRequestDTO;
import com.graduationProject.medicory.model.immunization.ImmunizationResponseDTO;
import com.graduationProject.medicory.model.allergies.AllergiesRequestDTO;
import com.graduationProject.medicory.model.allergies.AllergiesResponseDTO;
import com.graduationProject.medicory.model.chronicDisease.ChronicDiseasesRequestDTO;
import com.graduationProject.medicory.model.chronicDisease.ChronicDiseasesResponseDTO;
import com.graduationProject.medicory.model.surgery.SurgeryRequestDTO;
import com.graduationProject.medicory.model.surgery.SurgeryResponseDTO;

import java.util.List;

public interface DoctorMedicalHistoryService {

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
}
