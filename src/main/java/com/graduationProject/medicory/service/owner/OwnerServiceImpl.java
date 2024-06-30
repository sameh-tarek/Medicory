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
import com.graduationProject.medicory.mapper.usersMappers.*;
import com.graduationProject.medicory.model.allergies.AllergiesResponseDTO;
import com.graduationProject.medicory.model.chronicDisease.ChronicDiseasesResponseDTO;
import com.graduationProject.medicory.model.immunization.ImmunizationResponseDTO;
import com.graduationProject.medicory.model.medication.MedicationDTO;
import com.graduationProject.medicory.model.owner.OwnerResponseDTO;
import com.graduationProject.medicory.model.surgery.SurgeryResponseDTO;
import com.graduationProject.medicory.model.tests.ImagingTestResponseDTO;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicSearchResponseDTO;
import com.graduationProject.medicory.model.users.doctor.DoctorSearchResponseDTO;
import com.graduationProject.medicory.model.users.hospital.HospitalSearchResponseDTO;
import com.graduationProject.medicory.model.users.lab.LabSearchResponseDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacySearchResponseDTO;
import com.graduationProject.medicory.repository.MedicationsRepositories.CurrentScheduleRepository;
import com.graduationProject.medicory.repository.usersRepositories.*;
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
    private final CurrentScheduleRepository currentScheduleRepository;
    private final LabRepository labRepository;
    private final LabMapper labMapper;
    private final PharmacyRepository pharmacyRepository;
    private final PharmacyMpper pharmacyMapper;
    private final ClinicRepository clinicRepository;
    private final HospitalRepository hospitalRepository;
    private final ClinicMapper clinicMapper;
    private final HospitalMapper hospitalMapper;
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Override
    public OwnerResponseDTO getOwnerPersonalInformation(String userCode) {
        Owner owner= ownerRepository.findByUserCode(userCode)
                .orElseThrow(()-> new RecordNotFoundException("Owner with id " + userCode + " doesn't exist!"));
        return ownerMapper.toDTO(owner);
    }

    @Override
    public List<ChronicDiseasesResponseDTO> getOwnerChronicDiseases(String userCode) {
        Owner owner = ownerRepository.findByUserCode(userCode).orElseThrow(
                () -> new RecordNotFoundException("Owner with id: " + userCode+ " not exist!")
        );
        return owner.getChronicDiseases()
                .stream()
                .map(chronicDiseasesMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ChronicDiseasesResponseDTO getOwnerChronicDiseaseById(long diseaseId, String userCode) {
        Owner owner = ownerRepository.findByUserCode(userCode).orElseThrow(
                () -> new RecordNotFoundException("Owner with id: " + userCode + " not exist!")
        );
         return owner.getChronicDiseases()
                 .stream()
                 .filter(chronicDiseases -> chronicDiseases.getId() == diseaseId)
                 .map(chronicDiseasesMapper::toDTO)
                 .findFirst()
                 .orElseThrow(() -> new RecordNotFoundException("User with id: "+ userCode + " not have a chronic disease with id: " + diseaseId));
    }

    @Override
    public List<AllergiesResponseDTO> getOwnerAllergies(String userCode) {
        Owner owner = ownerRepository.findByUserCode(userCode).orElseThrow(
                () -> new RecordNotFoundException("Owner with id: " + userCode+ " not exist!"));
        return owner.getAllergies()
                .stream()
                .map(allergiesMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AllergiesResponseDTO getOwnerAllergyById(long diseaseId, String userCode) {
        Owner owner = ownerRepository.findByUserCode(userCode).orElseThrow(
                () -> new RecordNotFoundException("Owner with id: " + userCode + " not exist!")
        );
        return owner.getAllergies()
                .stream()
                .filter(allergies -> allergies.getId() == diseaseId)
                .map(allergiesMapper::toDTO)
                .findFirst()
                .orElseThrow(() -> new RecordNotFoundException("User with id: "+ userCode + " not have a Allergy with id: " + diseaseId));

    }

    @Override
    public List<ImmunizationResponseDTO> getOwnerImmunizations(String userCode) {
        Owner owner = ownerRepository.findByUserCode(userCode).orElseThrow(
                () -> new RecordNotFoundException("Owner with id: " + userCode+ " not exist!"));
        return owner.getImmunizations()
                .stream()
                .map(immunizationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ImmunizationResponseDTO getOwnerImmunizationById(long diseaseId, String userCode) {
        Owner owner = ownerRepository.findByUserCode(userCode).orElseThrow(
                () -> new RecordNotFoundException("Owner with id: " + userCode + " not exist!")
        );
        return owner.getImmunizations()
                .stream()
                .filter(immunization -> immunization.getId() == diseaseId)
                .map(immunizationMapper::toDTO)
                .findFirst()
                .orElseThrow(() -> new RecordNotFoundException("User with id: "+ userCode + " not have a immunization with id: " + diseaseId));

    }

    @Override
    public List<SurgeryResponseDTO> getOwnerSurgeries(String userCode) {
        Owner owner = ownerRepository.findByUserCode(userCode).orElseThrow(
                () -> new RecordNotFoundException("Owner with id: " + userCode+ " not exist!"));
        return owner.getSurgeries()
                .stream()
                .map(surgeryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SurgeryResponseDTO getOwnerSurgeryById(long diseaseId, String userCode) {
        Owner owner = ownerRepository.findByUserCode(userCode).orElseThrow(
                () -> new RecordNotFoundException("Owner with id: " + userCode + " not exist!")
        );
        return owner.getSurgeries()
                .stream()
                .filter(surgery -> surgery.getId() == diseaseId)
                .map(surgeryMapper::toDTO)
                .findFirst()
                .orElseThrow(() -> new RecordNotFoundException("User with id: "+ userCode + " not have a surgery with id: " + diseaseId));

    }

    @Override
    public List<LabTestResponseDTO> getOwnerLabTests(String userCode) {
        return ownerRepository.findByUserCode(userCode).orElseThrow(
                () -> new RecordNotFoundException("owner with id "+ userCode + " not found!")
        )
                .getLabTests()
                .stream()
                .map(labTestMapper::toDTO)
                .toList();
    }

    @Override
    public LabTestResponseDTO getOwnerLabTestByTestId(long testId, String userCode) {
        return ownerRepository.findByUserCode(userCode).orElseThrow(
                () -> new RecordNotFoundException("Owner with id: " + userCode+ " not exist!")
        )
                .getLabTests()
                .stream()
                .filter(labTest -> labTest.getId() == testId)
                .map(labTestMapper::toDTO)
                .findFirst()
                .orElseThrow(
                        () -> new RecordNotFoundException("User with id " + userCode + " not have a test with id " + testId)
                );
    }

    @Override
    public List<ImagingTestResponseDTO> getOwnerImagingTests(String userCode) {
        return ownerRepository.findByUserCode(userCode).orElseThrow(
                () -> new RecordNotFoundException("owner with id "+ userCode + " not found!")
        )
                .getImagingTests()
                .stream()
                .map(imagingTestMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ImagingTestResponseDTO getOwnerImagingTestByTestId(long testId, String userCode) {
        return ownerRepository.findByUserCode(userCode).orElseThrow(
                () -> new RecordNotFoundException("owner with id "+ userCode + " not found!")
        )
                .getImagingTests()
                .stream()
                .filter(imagingTest -> imagingTest.getId() == testId)
                .map(imagingTestMapper::toDTO)
                .findFirst()
                .orElseThrow(
                        () -> new RecordNotFoundException("User with id " + userCode + " not have an imaging test with id " + testId)
                );
    }

    @Override
    public List<MedicationDTO> getCurrentMedicationSchedule(String userCode) {

        Long ownerId = ownerRepository.findByUserCode(userCode).orElseThrow(
                () -> new RecordNotFoundException("owner with userCode "+ userCode + " not found!")
        ).getId();

        return currentScheduleRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new RecordNotFoundException("owner with userCode "+ userCode + " not have current medication schedule!")
        ).getMedications()
                .stream()
                .filter(Medication::isMedicationStatus)
                .map(medicationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LabSearchResponseDTO> getAllLabs() {
        return labRepository.findAll()
                .stream()
                .map(labMapper::toSearchResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LabSearchResponseDTO> getLabsByName(String labName) {
        return labRepository.findByName(labName)
                .stream()
                .map(labMapper::toSearchResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PharmacySearchResponseDTO> getAllPharmacies() {
        return pharmacyRepository.findAll()
                .stream()
                .map(pharmacyMapper :: toSearchResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PharmacySearchResponseDTO> getPharmaciesByName(String pharmacyName) {
        return pharmacyRepository.findByName(pharmacyName)
                .stream()
                .map(pharmacyMapper :: toSearchResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClinicSearchResponseDTO> getAllClinics() {
        return clinicRepository.findAll()
                .stream()
                .map(clinicMapper :: toSearchResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClinicSearchResponseDTO> getClinicsByName(String clinicName) {
        return clinicRepository.findByName(clinicName)
                .stream()
                .map(clinicMapper :: toSearchResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClinicSearchResponseDTO> getClinicsByDoctorName(String doctorName) {
        return clinicRepository.findClinicByOwnerName(doctorName).orElseThrow(
                () -> new RecordNotFoundException("Doctor " + doctorName + " has no clinics!")
        )
                .stream()
                .map(clinicMapper :: toSearchResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HospitalSearchResponseDTO> getAllHospitals() {
        return hospitalRepository.findAll()
                .stream()
                .map(hospitalMapper :: toSearchResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HospitalSearchResponseDTO> getHospitalsByName(String hospitalName) {
        return hospitalRepository.findHospitalByName(hospitalName)
                .stream()
                .map(hospitalMapper :: toSearchResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorSearchResponseDTO> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper :: toSearchResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorSearchResponseDTO> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findDoctorBySpecializationIsContainingIgnoreCase(specialization)
                .stream()
                .map(doctorMapper::toSearchResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorSearchResponseDTO> findDoctorByFirstName(String fName) {
        return doctorRepository.findDoctorByFirstName(fName)
                .stream()
                .map(doctorMapper::toSearchResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorSearchResponseDTO> findDoctorByMiddleName(String midName) {
        return doctorRepository.findDoctorByMiddleName(midName)
                .stream()
                .map(doctorMapper::toSearchResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorSearchResponseDTO> findDoctorByLastName(String lName) {
        return doctorRepository.findDoctorByLastName(lName)
                .stream()
                .map(doctorMapper::toSearchResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorSearchResponseDTO> findDoctorByFirstNameAndMiddleName(String fName, String midName) {
        return doctorRepository.findDoctorByFirstNameAndMiddleName(fName,midName)
                .stream()
                .map(doctorMapper::toSearchResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorSearchResponseDTO> findDoctorByFirstNameAndLastName(String fName, String lName) {
        return doctorRepository.findDoctorByFirstNameAndLastName(fName, lName)
                .stream()
                .map(doctorMapper::toSearchResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorSearchResponseDTO> findDoctorByMiddleNameAndLastName(String midName, String lName) {
        return doctorRepository.findDoctorByMiddleNameAndLastName(midName, lName)
                .stream()
                .map(doctorMapper::toSearchResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorSearchResponseDTO> findDoctorByFirstNameAndMiddleNameAndLastName(String fName, String midName, String lName) {
        return doctorRepository.findDoctorByFirstNameAndMiddleNameAndLastName(fName, midName, lName)
                .stream()
                .map(doctorMapper::toSearchResponseDTO)
                .collect(Collectors.toList());
    }


}
