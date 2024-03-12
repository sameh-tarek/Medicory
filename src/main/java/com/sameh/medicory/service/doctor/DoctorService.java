package com.sameh.medicory.service.doctor;

import com.sameh.medicory.model.AllergiesDTO;
import com.sameh.medicory.model.ImmunizationDTO;
import com.sameh.medicory.model.SurgeryDTO;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesDTO;
import com.sameh.medicory.model.labtests.LabTestDTO;
import com.sameh.medicory.model.labtests.LabTestRequiredDTO;
import com.sameh.medicory.model.patient.PatientPersonalInformation;

import java.util.List;

public interface DoctorService {
    PatientPersonalInformation getPatientPersonalInformation();
    List<ChronicDiseasesDTO> getPatientChronicDiseases();

    String addNewChronicDiseasesForPatient(ChronicDiseasesDTO chronicDiseasesDTO);

    List<AllergiesDTO> getPatientAllergies();

    String addNewAllergiesForPatient(AllergiesDTO allergiesDTO);

    List<ImmunizationDTO> getaAllPatientImmunizations();

    String addNewImmunizationForPatient(ImmunizationDTO immunizationDTO);

    List<SurgeryDTO> getPatientSurgicalHistory();

    String addNewSurgeryForPatient(SurgeryDTO surgeryDTO);

    List<LabTestDTO> findAllLabTestsForPatient();

    String deleteLabTestFromHistory(Long testId);
}
