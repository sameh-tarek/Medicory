package com.sameh.medicory.service;

import com.sameh.medicory.model.chronicDisease.ChronicDiseasesDTO;
import com.sameh.medicory.model.patient.PatientPersonalInformation;

import java.util.List;

public interface DoctorService {
    PatientPersonalInformation getPatientPersonalInformation();
    List<ChronicDiseasesDTO> getPatientChronicDiseases();
}
