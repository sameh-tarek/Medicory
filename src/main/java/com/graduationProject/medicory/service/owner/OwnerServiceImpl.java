package com.graduationProject.medicory.service.owner;

import com.graduationProject.medicory.entity.medicationEntities.Medication;
import com.graduationProject.medicory.entity.usersEntities.Owner;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.mapper.medicationsMappers.MedicationMapper;
import com.graduationProject.medicory.mapper.otherMappers.AllergiesMapper;
import com.graduationProject.medicory.mapper.otherMappers.ChronicDiseasesMapper;
import com.graduationProject.medicory.mapper.otherMappers.ImmunizationMapper;
import com.graduationProject.medicory.mapper.otherMappers.SurgeryMapper;
import com.graduationProject.medicory.mapper.testsMappers.ImagingTestMapper;
import com.graduationProject.medicory.mapper.testsMappers.LabTestMapper;
import com.graduationProject.medicory.mapper.usersMappers.OwnerMapper;
import com.graduationProject.medicory.model.allergies.AllergiesResponseDTO;
import com.graduationProject.medicory.model.chronicDisease.ChronicDiseasesResponseDTO;
import com.graduationProject.medicory.model.immunization.ImmunizationResponseDTO;
import com.graduationProject.medicory.model.medication.MedicationDTO;
import com.graduationProject.medicory.model.owner.OwnerDTO;
import com.graduationProject.medicory.model.surgery.SurgeryResponseDTO;
import com.graduationProject.medicory.model.tests.ImagingTestResponseDTO;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import com.graduationProject.medicory.repository.usersRepositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;
    private final ChronicDiseasesMapper chronicDiseasesMapper;
    private final AllergiesMapper allergiesMapper;
    private final ImmunizationMapper immunizationMapper;
    private final SurgeryMapper surgeryMapper;
    private final LabTestMapper labTestMapper;
    private final ImagingTestMapper imagingTestMapper;
    private final MedicationMapper medicationMapper;

    @Override
    public OwnerDTO getOwnerPersonalInformation(long id) {
        Owner owner= ownerRepository.findAllByUserId(id)
                .orElseThrow(()-> new RecordNotFoundException("Owner with id " + id + " doesn't exist!"));
        return ownerMapper.toDTO(owner);
    }

    @Override
    public List<ChronicDiseasesResponseDTO> getOwnerChronicDiseases(long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException("Owner with id: " + id+ " not exist!")
        );
        return owner.getChronicDiseases()
                .stream()
                .map(chronicDiseasesMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ChronicDiseasesResponseDTO getOwnerChronicDiseaseById(long diseaseId, long userId) {
        Owner owner = ownerRepository.findById(userId).orElseThrow(
                () -> new RecordNotFoundException("Owner with id: " + userId + " not exist!")
        );
         return owner.getChronicDiseases()
                 .stream()
                 .filter(chronicDiseases -> chronicDiseases.getId() == diseaseId)
                 .map(chronicDiseasesMapper::toDTO)
                 .findFirst()
                 .orElseThrow(() -> new RecordNotFoundException("User with id: "+ userId + " not have a chronic disease with id: " + diseaseId));
    }

    @Override
    public List<AllergiesResponseDTO> getOwnerAllergies(long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException("Owner with id: " + id+ " not exist!"));
        return owner.getAllergies()
                .stream()
                .map(allergiesMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AllergiesResponseDTO getOwnerAllergyById(long diseaseId, long userId) {
        Owner owner = ownerRepository.findById(userId).orElseThrow(
                () -> new RecordNotFoundException("Owner with id: " + userId + " not exist!")
        );
        return owner.getAllergies()
                .stream()
                .filter(allergies -> allergies.getId() == diseaseId)
                .map(allergiesMapper::toDTO)
                .findFirst()
                .orElseThrow(() -> new RecordNotFoundException("User with id: "+ userId + " not have a Allergy with id: " + diseaseId));

    }

    @Override
    public List<ImmunizationResponseDTO> getOwnerImmunizations(long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException("Owner with id: " + id+ " not exist!"));
        return owner.getImmunizations()
                .stream()
                .map(immunizationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ImmunizationResponseDTO getOwnerImmunizationById(long diseaseId, long userId) {
        Owner owner = ownerRepository.findById(userId).orElseThrow(
                () -> new RecordNotFoundException("Owner with id: " + userId + " not exist!")
        );
        return owner.getImmunizations()
                .stream()
                .filter(immunization -> immunization.getId() == diseaseId)
                .map(immunizationMapper::toDTO)
                .findFirst()
                .orElseThrow(() -> new RecordNotFoundException("User with id: "+ userId + " not have a immunization with id: " + diseaseId));

    }

    @Override
    public List<SurgeryResponseDTO> getOwnerSurgeries(long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException("Owner with id: " + id+ " not exist!"));
        return owner.getSurgeries()
                .stream()
                .map(surgeryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SurgeryResponseDTO getOwnerSurgeryById(long diseaseId, long userId) {
        Owner owner = ownerRepository.findById(userId).orElseThrow(
                () -> new RecordNotFoundException("Owner with id: " + userId + " not exist!")
        );
        return owner.getSurgeries()
                .stream()
                .filter(surgery -> surgery.getId() == diseaseId)
                .map(surgeryMapper::toDTO)
                .findFirst()
                .orElseThrow(() -> new RecordNotFoundException("User with id: "+ userId + " not have a surgery with id: " + diseaseId));

    }

    @Override
    public List<LabTestResponseDTO> getOwnerLabTests(long userId) {
        return ownerRepository.findById(userId).orElseThrow(
                () -> new RecordNotFoundException("owner with id "+ userId + " not found!")
        )
                .getLabTests()
                .stream()
                .map(labTestMapper::toDTO)
                .toList();
    }

    @Override
    public LabTestResponseDTO getOwnerLabTestByTestId(long testId, long userId) {
        return ownerRepository.findById(userId).orElseThrow(
                () -> new RecordNotFoundException("Owner with id: " + userId+ " not exist!")
        )
                .getLabTests()
                .stream()
                .filter(labTest -> labTest.getId() == testId)
                .map(labTestMapper::toDTO)
                .findFirst()
                .orElseThrow(
                        () -> new RecordNotFoundException("User with id " + userId + " not have a test with id " + testId)
                );
    }

    @Override
    public List<ImagingTestResponseDTO> getOwnerImagingTests(long userId) {
        return ownerRepository.findById(userId).orElseThrow(
                () -> new RecordNotFoundException("owner with id "+ userId + " not found!")
        )
                .getImagingTests()
                .stream()
                .map(imagingTestMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ImagingTestResponseDTO getOwnerImagingTestByTestId(long testId, long userId) {
        return ownerRepository.findById(userId).orElseThrow(
                () -> new RecordNotFoundException("owner with id "+ userId + " not found!")
        )
                .getImagingTests()
                .stream()
                .filter(imagingTest -> imagingTest.getId() == testId)
                .map(imagingTestMapper::toDTO)
                .findFirst()
                .orElseThrow(
                        () -> new RecordNotFoundException("User with id " + userId + " not have an imaging test with id " + testId)
                );
    }

    @Override
    public List<MedicationDTO> getMedicationSchedule(long userId) {
        List<MedicationDTO> medicationDTOS = ownerRepository.findById(userId).orElseThrow(
                () -> new RecordNotFoundException("owner with id "+ userId + " not found!")
        )
                .getCurrentSchedule()
                .getMedications()
                .stream()
                .filter(Medication::isMedicationStatus)
                .map(medicationMapper::toDTO)
                .collect(Collectors.toList());

        log.info("Medication schedule for owner with id {} is {}", userId, medicationDTOS);
        return medicationDTOS;
    }

}
