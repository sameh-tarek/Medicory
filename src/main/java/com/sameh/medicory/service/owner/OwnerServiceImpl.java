package com.sameh.medicory.service.owner;

import com.sameh.medicory.entity.otherEntities.ChronicDiseases;
import com.sameh.medicory.entity.usersEntities.Owner;
import com.sameh.medicory.exception.RecordNotFoundException;
import com.sameh.medicory.mapper.*;
import com.sameh.medicory.model.allergies.AllergiesResponseDTO;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesResponseDTO;
import com.sameh.medicory.model.immunization.ImmunizationResponseDTO;
import com.sameh.medicory.model.owner.OwnerDTO;
import com.sameh.medicory.model.surgery.SurgeryResponseDTO;
import com.sameh.medicory.repository.OwnerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;
    private final ChronicDiseasesMapper chronicDiseasesMapper;
    private final AllergiesMapper allergiesMapper;
    private final ImmunizationMapper immunizationMapper;
    private final SurgeryMapper surgeryMapper;

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

}
