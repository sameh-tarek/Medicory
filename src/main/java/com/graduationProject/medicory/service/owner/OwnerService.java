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
    OwnerDTO getOwnerPersonalInformation(long id);

    List<ChronicDiseasesResponseDTO> getOwnerChronicDiseases(long id);

    ChronicDiseasesResponseDTO getOwnerChronicDiseaseById(long diseaseId, long userId);

    List<AllergiesResponseDTO> getOwnerAllergies(long id);

    AllergiesResponseDTO getOwnerAllergyById(long diseaseId, long userId);

    List<ImmunizationResponseDTO> getOwnerImmunizations(long id);

    ImmunizationResponseDTO getOwnerImmunizationById(long diseaseId, long userId);

    List<SurgeryResponseDTO> getOwnerSurgeries(long id);

    SurgeryResponseDTO getOwnerSurgeryById(long diseaseId, long userId);

    List<LabTestResponseDTO> getOwnerLabTests(long userId);

    LabTestResponseDTO getOwnerLabTestByTestId(long testId, long userId);

    List<ImagingTestResponseDTO> getOwnerImagingTests(long userId);

    ImagingTestResponseDTO getOwnerImagingTestByTestId(long testId, long userId);

    List<MedicationDTO> getMedicationSchedule(long userId);
}
