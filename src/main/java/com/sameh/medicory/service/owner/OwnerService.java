package com.sameh.medicory.service.owner;

import com.sameh.medicory.entity.testsEntities.LabTest;
import com.sameh.medicory.entity.usersEntities.Owner;
import com.sameh.medicory.model.allergies.AllergiesResponseDTO;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesResponseDTO;
import com.sameh.medicory.model.immunization.ImmunizationResponseDTO;
import com.sameh.medicory.model.medication.MedicationScheduleDTO;
import com.sameh.medicory.model.owner.OwnerDTO;
import com.sameh.medicory.model.surgery.SurgeryResponseDTO;
import com.sameh.medicory.model.tests.ImagingTestDTO;
import com.sameh.medicory.model.tests.LabTestDTO;
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

    List<LabTestDTO> getOwnerLabTests(long userId);

    LabTestDTO getOwnerLabTestByTestId(long testId, long userId);

    List<ImagingTestDTO> getOwnerImagingTests(long userId);

    ImagingTestDTO getOwnerImagingTestByTestId(long testId, long userId);

    List<MedicationScheduleDTO> getMedicationSchedule(long userId);
}
