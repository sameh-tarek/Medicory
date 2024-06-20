package com.graduationProject.medicory.service.emergency.impl;

import com.graduationProject.medicory.entity.medicationEntities.Medication;
import com.graduationProject.medicory.entity.medicationEntities.Medicine;
import com.graduationProject.medicory.entity.otherEntities.Allergies;
import com.graduationProject.medicory.entity.otherEntities.ChronicDiseases;
import com.graduationProject.medicory.entity.otherEntities.Surgery;
import com.graduationProject.medicory.entity.usersEntities.Owner;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.mapper.phonesMappers.RelativePhoneMapper;
import com.graduationProject.medicory.model.emergency.EmergencyDTO;
import com.graduationProject.medicory.model.phones.RelativePhoneNumberDTO;
import com.graduationProject.medicory.repository.MedicationsRepositories.MedicationRepository;
import com.graduationProject.medicory.repository.otherRepositories.AllergiesRepository;
import com.graduationProject.medicory.repository.otherRepositories.ChronicDiseasesRepository;
import com.graduationProject.medicory.repository.usersRepositories.OwnerRepository;
import com.graduationProject.medicory.repository.otherRepositories.SurgeryRepository;
import com.graduationProject.medicory.service.emergency.EmergencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmergencyServiceImpl implements EmergencyService {

    private final OwnerRepository ownerRepository;
    private final AllergiesRepository allergiesRepository;
    private final SurgeryRepository surgeryRepository;
    private final ChronicDiseasesRepository chronicDiseasesRepository;
    private final MedicationRepository  medicationRepository;
    private final RelativePhoneMapper relativePhoneMapper;


    @Override
    public EmergencyDTO getEmergencyInfo(String ownerCode) {
            log.info("Emergency case for user with code {}",ownerCode);
            Owner owner = ownerRepository.findByUserCode(ownerCode)
                    .orElseThrow(()-> new RecordNotFoundException("Card owner with code " + ownerCode+ " not found") );
                Long ownerId = owner.getId();

                //Allergies
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

                //Medicines
                List< Medication> medications = medicationRepository.findByOwner(owner);
                List<Medicine> medicines = medications.stream()
                        .map(Medication::getMedicine)
                        .collect(Collectors.toList());
                List<String> medicinesNames=medicines.stream()
                        .map(Medicine ::getName)
                        .collect(Collectors.toList());

                List<RelativePhoneNumberDTO> relativePhoneNumbers =relativePhoneMapper.toDTO(owner.getRelativePhoneNumbers());

                EmergencyDTO emergencyInfo = new EmergencyDTO();
                emergencyInfo.setOwnerName(owner.getFirstName() + " " + owner.getMiddleName() + " " + owner.getLastName());
                emergencyInfo.setBloodType(owner.getBloodType().name());
                emergencyInfo.setAllergies(allergyNames);
                emergencyInfo.setSurgeries(surgeriesNames);
                emergencyInfo.setChronicDiseases(chronicDiseasesNames);
                emergencyInfo.setRelativePhoneNumbers(relativePhoneNumbers);
                emergencyInfo.setMedicines(medicinesNames);

                log.info("Emergency information for user with code {} :{}",ownerCode,emergencyInfo);
                return emergencyInfo;
        }


}
