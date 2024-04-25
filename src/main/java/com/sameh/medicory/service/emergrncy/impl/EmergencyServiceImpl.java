package com.sameh.medicory.service.emergrncy.impl;

import com.sameh.medicory.entity.otherEntities.Allergies;
import com.sameh.medicory.entity.otherEntities.ChronicDiseases;
import com.sameh.medicory.entity.otherEntities.Surgery;
import com.sameh.medicory.entity.usersEntities.Owner;
import com.sameh.medicory.exception.RecordNotFoundException;
import com.sameh.medicory.mapper.AllergiesMapper;
import com.sameh.medicory.mapper.ChronicDiseasesMapper;
import com.sameh.medicory.mapper.SurgeryMapper;
import com.sameh.medicory.model.emergency.EmergencyDTO;
import com.sameh.medicory.repository.AllergiesRepository;
import com.sameh.medicory.repository.ChronicDiseasesRepository;
import com.sameh.medicory.repository.OwnerRepository;
import com.sameh.medicory.repository.SurgeryRepository;
import com.sameh.medicory.service.emergrncy.EmergencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmergencyServiceImpl implements EmergencyService {

    private final OwnerRepository ownerRepository;
    private final AllergiesRepository allergiesRepository;
    private  final AllergiesMapper allergiesMapper;
    private final SurgeryRepository surgeryRepository;
    private final SurgeryMapper surgeryMapper;
    private final ChronicDiseasesMapper chronicDiseasesMapper;
    private final ChronicDiseasesRepository chronicDiseasesRepository;

    @Override
    public EmergencyDTO getEmergencyInfo(Long ownerId) {
        if (ownerId > 0) {
            Optional<Owner> ownerExist = ownerRepository.findById(ownerId);
            if (ownerExist.isPresent()) {
                Owner owner = ownerExist.get();
                //allergies
                List<Allergies> allergies =allergiesRepository.findAllergiesByOwnerId(ownerId);

                List<String> allergyNames = allergies.stream()
                        .map(Allergies::getName)
                        .collect(Collectors.toList());

             //Surgeries
                List<Surgery> surgeries =surgeryRepository.findSurgeriesByOwnerId(ownerId);
                List<String> surgeriesNames= surgeries.stream()
                        .map(Surgery ::getName)
                        .collect(Collectors.toList());

                //ChronicDiseases
                List<ChronicDiseases> chronicDiseases =chronicDiseasesRepository.findChronicDiseasesByOwnerId(ownerId);
                List<String> chronicDiseasesNames=chronicDiseases.stream()
                        .map(ChronicDiseases ::getName)
                        .collect(Collectors.toList());

                EmergencyDTO emergencyInfo = new EmergencyDTO();
                emergencyInfo.setOwnerName(owner.getFirstName() + " " + owner.getMiddleName() + " " + owner.getLastName());
                emergencyInfo.setBloodType(owner.getBloodType().name());
                emergencyInfo.setAllergies(allergyNames);
                emergencyInfo.setSurgeries(surgeriesNames);
                emergencyInfo.setChronicDiseases(chronicDiseasesNames);
                emergencyInfo.setRelativePhoneNumbers(owner.getUser().getRelativePhoneNumbers());

                return emergencyInfo;
            } else {
                throw new RecordNotFoundException("No owner with id " + ownerId + " not found");
            }
        } else {
            throw new IllegalArgumentException("Invalid id " + ownerId);
        }
    }

}
