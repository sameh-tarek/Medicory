package com.graduationProject.medicory.service.doctor.impl;

import com.graduationProject.medicory.entity.otherEntities.Allergies;
import com.graduationProject.medicory.entity.otherEntities.ChronicDiseases;
import com.graduationProject.medicory.entity.otherEntities.Immunization;
import com.graduationProject.medicory.entity.otherEntities.Surgery;
import com.graduationProject.medicory.entity.usersEntities.Owner;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.mapper.otherMappers.AllergiesMapper;
import com.graduationProject.medicory.mapper.otherMappers.ChronicDiseasesMapper;
import com.graduationProject.medicory.mapper.otherMappers.ImmunizationMapper;
import com.graduationProject.medicory.mapper.otherMappers.SurgeryMapper;
import com.graduationProject.medicory.model.immunization.ImmunizationRequestDTO;
import com.graduationProject.medicory.model.immunization.ImmunizationResponseDTO;
import com.graduationProject.medicory.model.chronicDisease.ChronicDiseasesRequestDTO;
import com.graduationProject.medicory.model.chronicDisease.ChronicDiseasesResponseDTO;
import com.graduationProject.medicory.model.surgery.SurgeryRequestDTO;
import com.graduationProject.medicory.model.surgery.SurgeryResponseDTO;
import com.graduationProject.medicory.model.allergies.AllergiesRequestDTO;
import com.graduationProject.medicory.model.allergies.AllergiesResponseDTO;
import com.graduationProject.medicory.repository.otherRepositories.AllergiesRepository;
import com.graduationProject.medicory.repository.otherRepositories.ChronicDiseasesRepository;
import com.graduationProject.medicory.repository.otherRepositories.ImmunizationRepository;
import com.graduationProject.medicory.repository.otherRepositories.SurgeryRepository;
import com.graduationProject.medicory.repository.usersRepositories.OwnerRepository;
import com.graduationProject.medicory.service.doctor.DoctorMedicalHistoryService;
import com.graduationProject.medicory.utils.OwnerContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorMedicalHistoryServiceImpl implements DoctorMedicalHistoryService {

    private final OwnerRepository ownerRepository;
    private final ChronicDiseasesMapper chronicDiseasesMapper;
    private final OwnerContext ownerContext;
    private final AllergiesMapper allergiesMapper;
    private final ImmunizationMapper immunizationMapper;
    private final SurgeryMapper surgeryMapper;
    private final ChronicDiseasesRepository chronicDiseasesRepository;
    private final AllergiesRepository allergiesRepository;
    private final ImmunizationRepository immunizationRepository;
    private final SurgeryRepository surgeryRepository;


    @Override
    public List<ChronicDiseasesResponseDTO> getPatientChronicDiseases(String userCode) {
        Owner patientOwner = ownerContext.getCurrentOwner(userCode);
        log.info("Doctor Wants to get patient Chronic Diseases for owner with id {}", patientOwner.getId());
        List<ChronicDiseases> chronicDiseases = patientOwner.getChronicDiseases();
        List<ChronicDiseasesResponseDTO> chronicDiseasesResponseDTOS = chronicDiseases
                .stream()
                .map(chronicDiseasesMapper::toDTO)
                .collect(Collectors.toList());
        log.info("patient Chronic Diseases for owner with id: {} is {}", patientOwner.getId(), chronicDiseases);
        return chronicDiseasesResponseDTOS;
    }

    @Override
    public String addNewChronicDiseasesForPatient(ChronicDiseasesRequestDTO chronicDiseasesRequestDTO, String userCode) {
        Owner patientOwner = ownerContext.getCurrentOwner(userCode);
        log.info("Doctor wants to add a new chronic disease: {} for patient with owner id: {}", chronicDiseasesRequestDTO, patientOwner.getId());

        ChronicDiseases newChronicDisease = chronicDiseasesMapper.toEntity(chronicDiseasesRequestDTO);
        newChronicDisease.setOwner(patientOwner);
        newChronicDisease.setCreatedAt(LocalDateTime.now());
        newChronicDisease.setUpdatedAt(LocalDateTime.now());
        List<ChronicDiseases> patientChronicDiseases = patientOwner.getChronicDiseases();
        patientChronicDiseases.add(newChronicDisease);
        log.info("patientChronicDiseases {}", patientChronicDiseases);
        patientOwner.setChronicDiseases(patientChronicDiseases);
        ownerRepository.save(patientOwner);

        log.info("The new chronic disease added successfully. All the patient chronic diseases: {}", patientOwner.getChronicDiseases());
        return "Chronic Disease added successfully";
    }

    @Override
    public ChronicDiseasesResponseDTO findChronicDiseasesById(Long diseasesId) {
        ChronicDiseases chronicDisease =  chronicDiseasesRepository.findById(diseasesId)
                .orElseThrow(() -> new RecordNotFoundException("This Chronic Disease with id "+diseasesId+" doesn't exist"));
        ChronicDiseasesResponseDTO chronicDiseasesResponseDTO = chronicDiseasesMapper.toDTO(chronicDisease);
        log.info("Chronic Disease with id  {}, details {}", diseasesId, chronicDiseasesResponseDTO);
        return chronicDiseasesResponseDTO;
    }

    @Override
    public String updateChronicDisease(ChronicDiseasesRequestDTO chronicDiseasesRequestDTO, Long diseasesId, String userCode) {
        Owner patientOwner = ownerContext.getCurrentOwner(userCode);
        log.info("Doctor want to update chronic disease with id {}", diseasesId);
        ChronicDiseases existChronicDiseases = chronicDiseasesRepository.findById(diseasesId)
                .orElseThrow(() -> new RecordNotFoundException("This Chronic Disease with id "+diseasesId+" doesn't exist"));
        ChronicDiseases updatedChronicDiseases = chronicDiseasesMapper.toEntity(chronicDiseasesRequestDTO);
        updatedChronicDiseases.setOwner(patientOwner);
        updatedChronicDiseases.setCreatedAt(existChronicDiseases.getCreatedAt());
        updatedChronicDiseases.setUpdatedAt(LocalDateTime.now());
        updatedChronicDiseases.setId(diseasesId);
        chronicDiseasesRepository.save(updatedChronicDiseases);
        log.info("Chronic Disease updated successfully this before update {} and This after update {}", existChronicDiseases, updatedChronicDiseases);
        return "Success";
    }

    @Override
    public String deleteChronicDiseases(Long diseasesId) {
        log.info("Doctor want to delete Chronic chronic disease with id {}", diseasesId);
        ChronicDiseases chronicDisease = chronicDiseasesRepository.findById(diseasesId)
                .orElseThrow(() -> new RecordNotFoundException("This Chronic Disease with id "+diseasesId+" Already doesn't exist"));
        chronicDiseasesRepository.delete(chronicDisease);
        log.info("Chronic Disease deleted successfully");
        return "success";
    }




    @Override
    public List<AllergiesResponseDTO> getPatientAllergies(String userCode) {
        Owner patientOwner = ownerContext.getCurrentOwner(userCode);
        log.info("Doctor want to get patient Allergies owner with id: {}", patientOwner.getAllergies());
        List<AllergiesResponseDTO> allergiesResponseDTOS = patientOwner.getAllergies()
                .stream()
                .map(allergiesMapper::toDTO)
                .collect(Collectors.toList());
        log.info("All Patient Allergies {}", allergiesResponseDTOS);
        return allergiesResponseDTOS;
    }

    @Override
    public String addNewAllergiesForPatient(AllergiesRequestDTO allergiesRequestDTO, String userCode) {
        Owner patientOwner = ownerContext.getCurrentOwner(userCode);
        log.info("Doctor want to add new Allergies: {} for patient owner his ID: {}", allergiesRequestDTO,patientOwner.getId());
        Allergies allergies = allergiesMapper.toEntity(allergiesRequestDTO);
        allergies.setOwner(patientOwner);
        allergies.setCreatedAt(LocalDateTime.now());
        allergies.setUpdatedAt(LocalDateTime.now());
        List<Allergies> patientAllergies = patientOwner.getAllergies();
        patientAllergies.add(allergies);
        patientOwner.setAllergies(patientAllergies);
        ownerRepository.save(patientOwner);
        log.info("the new Allergies Added successfully {}", patientOwner.getAllergies());
        return "New Allergies Added successfully";
    }

    @Override
    public AllergiesResponseDTO findAllergiesById(Long allergiesId) {
        Allergies allergies = allergiesRepository.findById(allergiesId)
                .orElseThrow(() -> new RecordNotFoundException("This Allergies with id "+allergiesId+" doesn't exist"));
          AllergiesResponseDTO allergiesResponseDTO = allergiesMapper.toDTO(allergies);
        log.info("Allergies with id  {}, details {}", allergiesId, allergiesResponseDTO);
        return allergiesResponseDTO;
    }

    @Override
    public String updateAllergies(AllergiesRequestDTO allergiesRequestDTO, Long allergiesId, String userCode) {
        Owner patientOwner = ownerContext.getCurrentOwner(userCode);
        log.info("Doctor want to update Allergies with id {}", allergiesId);
        Allergies existAllergies = allergiesRepository.findById(allergiesId)
                .orElseThrow(() -> new RecordNotFoundException("This Allergies with id "+allergiesId+" doesn't exist"));
        Allergies updatedAllergies = allergiesMapper.toEntity(allergiesRequestDTO);
        updatedAllergies.setOwner(patientOwner);
        updatedAllergies.setCreatedAt(existAllergies.getCreatedAt());
        updatedAllergies.setUpdatedAt(LocalDateTime.now());
        updatedAllergies.setId(allergiesId);
        allergiesRepository.save(updatedAllergies);
        log.info("Allergies updated successfully this before update {} and This after update {}", existAllergies, updatedAllergies);
        return "Success";
    }

    @Override
    public String deleteAllergies(Long allergiesId) {
        log.info("Doctor want to delete Allergies disease with id {}", allergiesId);
        Allergies allergies = allergiesRepository.findById(allergiesId)
                .orElseThrow(() -> new RecordNotFoundException("This Allergies with id "+allergiesId+" Already doesn't exist"));
        allergiesRepository.delete(allergies);
        log.info("Allergies deleted successfully");
        return "success";
    }





    @Override
    public List<ImmunizationResponseDTO> getaAllPatientImmunizations(String userCode) {
        Owner patientOwner = ownerContext.getCurrentOwner(userCode);
        log.info("Doctor want to get All Immunizations for patient owner with id {}", patientOwner.getId());
        List<ImmunizationResponseDTO> immunizationResponseDTOS = patientOwner.getImmunizations()
                .stream()
                .map(immunizationMapper::toDTO)
                .collect(Collectors.toList());
        log.info("All Immunizations {}", immunizationResponseDTOS);
        return immunizationResponseDTOS;
    }

    @Override
    public String addNewImmunizationForPatient(ImmunizationRequestDTO immunizationRequestDTO, String userCode) {
        Owner patientOwner = ownerContext.getCurrentOwner(userCode);
        log.info("Doctor want to add this new Immunization {}, for owner with id {}", immunizationRequestDTO, patientOwner.getId());
        Immunization newImmunization = immunizationMapper.toEntity(immunizationRequestDTO);
        newImmunization.setOwner(patientOwner);
        newImmunization.setCreatedAt(LocalDateTime.now());
        newImmunization.setUpdatedAt(LocalDateTime.now());
        List<Immunization> allPatientImmunizations = patientOwner.getImmunizations();
        allPatientImmunizations.add(newImmunization);
        patientOwner.setImmunizations(allPatientImmunizations);
        ownerRepository.save(patientOwner);
        log.info("the New immunization added successfully {}", patientOwner.getImmunizations());
        return "New Immunization Added Successfully";
    }

    @Override
    public ImmunizationResponseDTO findImmunizationById(Long immunizationId) {
        Immunization immunization = immunizationRepository.findById(immunizationId)
                .orElseThrow(() -> new RecordNotFoundException("This immunization with id "+immunizationId+" doesn't exist"));
        ImmunizationResponseDTO immunizationResponseDTO = immunizationMapper.toDTO(immunization);
        log.info("immunization with id  {}, details {}", immunizationId, immunizationResponseDTO);
        return immunizationResponseDTO;
    }

    @Override
    public String updateImmunization(ImmunizationRequestDTO immunizationRequestDTO, Long immunizationId, String userCode) {
        Owner patientOwner = ownerContext.getCurrentOwner(userCode);
        log.info("Doctor want to update Immunization with id {}", immunizationId);
        Immunization existImmunization = immunizationRepository.findById(immunizationId)
                .orElseThrow(() -> new RecordNotFoundException("This Immunization with id "+immunizationId+" doesn't exist"));
        Immunization updatedImmunization = immunizationMapper.toEntity(immunizationRequestDTO);
        updatedImmunization.setOwner(patientOwner);
        updatedImmunization.setCreatedAt(existImmunization.getCreatedAt());
        updatedImmunization.setUpdatedAt(LocalDateTime.now());
        updatedImmunization.setId(immunizationId);
        immunizationRepository.save(updatedImmunization);
        log.info("Immunization updated successfully this before update {} and This after update {}", existImmunization, updatedImmunization);
        return "Success";
    }

    @Override
    public String deleteImmunization(Long immunizationId) {
        log.info("Doctor want to delete Immunization disease with id {}", immunizationId);
        Immunization immunization = immunizationRepository.findById(immunizationId)
                .orElseThrow(() -> new RecordNotFoundException("This Immunization with id "+immunizationId+" Already doesn't exist"));
        immunizationRepository.delete(immunization);
        log.info("Immunization deleted successfully");
        return "success";

    }




    @Override
    public List<SurgeryResponseDTO> getPatientSurgicalHistory(String userCode) {
        Owner patientOwner = ownerContext.getCurrentOwner(userCode);
        log.info("Doctor want to Surgical History for patient owner with id {}", patientOwner.getId());
        List<SurgeryResponseDTO> surgeryResponseDTOS = patientOwner.getSurgeries()
                .stream()
                .map(surgeryMapper::toDTO)
                .collect(Collectors.toList());
        log.info("patient Surgical History {}", surgeryResponseDTOS);
        return surgeryResponseDTOS;
    }

    @Override
    public String addNewSurgeryForPatient(SurgeryRequestDTO surgeryRequestDTO, String userCode) {
        Owner patientOwner = ownerContext.getCurrentOwner(userCode);
        log.info("Doctor want to add this new SurgeryRepository {}, for owner with id {}", surgeryRequestDTO, patientOwner.getId());
        Surgery surgery = surgeryMapper.toEntity(surgeryRequestDTO);
        surgery.setOwner(patientOwner);
        surgery.setCreatedAt(LocalDateTime.now());
        surgery.setUpdatedAt(LocalDateTime.now());
        List<Surgery> allPatientSurgeries = patientOwner.getSurgeries();
        allPatientSurgeries.add(surgery);
        patientOwner.setSurgeries(allPatientSurgeries);
        ownerRepository.save(patientOwner);
        log.info("the New SurgeryRepository added successfully {}", patientOwner.getSurgeries());
        return "New SurgeryRepository Added Successfully";
    }

    @Override
    public SurgeryResponseDTO findSurgeryById(Long surgeryId) {
        Surgery surgery = surgeryRepository.findById(surgeryId)
                .orElseThrow(() -> new RecordNotFoundException("This Surgery with id "+surgeryId+" doesn't exist"));
        SurgeryResponseDTO surgeryResponseDTO = surgeryMapper.toDTO(surgery);
        log.info("Surgery with id  {}, details {}", surgeryId, surgeryResponseDTO);
        return surgeryResponseDTO;
    }

    @Override
    public String updateSurgery(SurgeryRequestDTO surgeryRequestDTO, Long surgeryId, String userCode) {
        Owner patientOwner = ownerContext.getCurrentOwner(userCode);
        log.info("Doctor want to update Surgery with id {}", surgeryId);
        Surgery existSurgery = surgeryRepository.findById(surgeryId)
                .orElseThrow(() -> new RecordNotFoundException("This Surgery with id "+surgeryId+" doesn't exist"));
        Surgery updatedSurgery = surgeryMapper.toEntity(surgeryRequestDTO);
        updatedSurgery.setOwner(patientOwner);
        updatedSurgery.setCreatedAt(existSurgery.getCreatedAt());
        updatedSurgery.setUpdatedAt(LocalDateTime.now());
        updatedSurgery.setId(surgeryId);
        surgeryRepository.save(updatedSurgery);
        log.info("Surgery updated successfully this before update {} and This after update {}",
                existSurgery, updatedSurgery);
        return "Success";
    }

    @Override
    public String deleteSurgery(Long surgeryId) {
        log.info("Doctor want to delete Surgery disease with id {}", surgeryId);
        Surgery surgery = surgeryRepository.findById(surgeryId)
                .orElseThrow(() ->
                        new RecordNotFoundException("This Surgery with id " + surgeryId + " Already doesn't exist"));
        surgeryRepository.delete(surgery);
        log.info("Surgery deleted successfully");
        return "success";
    }
}
