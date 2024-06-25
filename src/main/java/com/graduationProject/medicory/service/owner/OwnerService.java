package com.graduationProject.medicory.service.owner;

import com.graduationProject.medicory.model.allergies.AllergiesResponseDTO;
import com.graduationProject.medicory.model.chronicDisease.ChronicDiseasesResponseDTO;
import com.graduationProject.medicory.model.immunization.ImmunizationResponseDTO;
import com.graduationProject.medicory.model.medication.MedicationDTO;
import com.graduationProject.medicory.model.owner.OwnerDTO;
import com.graduationProject.medicory.model.surgery.SurgeryResponseDTO;
import com.graduationProject.medicory.model.tests.ImagingTestResponseDTO;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OwnerService {
    OwnerDTO getOwnerPersonalInformation(String userCode);

    List<ChronicDiseasesResponseDTO> getOwnerChronicDiseases(String userCode);

    ChronicDiseasesResponseDTO getOwnerChronicDiseaseById(long diseaseId, String userCode);

    List<AllergiesResponseDTO> getOwnerAllergies(String userCode);

    AllergiesResponseDTO getOwnerAllergyById(long diseaseId, String userCode);

    List<ImmunizationResponseDTO> getOwnerImmunizations(String userCode);

    ImmunizationResponseDTO getOwnerImmunizationById(long diseaseId, String userCode);

    List<SurgeryResponseDTO> getOwnerSurgeries(String userCode);

    SurgeryResponseDTO getOwnerSurgeryById(long diseaseId, String userCode);

    List<LabTestResponseDTO> getOwnerLabTests(String userCode);

    LabTestResponseDTO getOwnerLabTestByTestId(long testId, String userCode);

    List<ImagingTestResponseDTO> getOwnerImagingTests(String userCode);

    ImagingTestResponseDTO getOwnerImagingTestByTestId(long testId, String userCode);

    List<MedicationDTO> getCurrentMedicationSchedule(String userCode);
}
