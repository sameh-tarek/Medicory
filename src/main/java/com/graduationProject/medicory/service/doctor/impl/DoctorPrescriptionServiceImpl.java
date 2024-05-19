package com.graduationProject.medicory.service.doctor.impl;

import com.graduationProject.medicory.entity.medicationEntities.Medication;
import com.graduationProject.medicory.entity.medicationEntities.Medicine;
import com.graduationProject.medicory.entity.medicationEntities.Prescription;
import com.graduationProject.medicory.entity.testsEntities.ImagingTest;
import com.graduationProject.medicory.entity.testsEntities.LabTest;
import com.graduationProject.medicory.entity.usersEntities.Doctor;
import com.graduationProject.medicory.entity.usersEntities.Owner;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.mapper.testsMappers.ImagingTestMapper;
import com.graduationProject.medicory.mapper.testsMappers.LabTestMapper;
import com.graduationProject.medicory.mapper.medicationsMappers.MedicationMapper;
import com.graduationProject.medicory.model.medication.MedicationDTO;
import com.graduationProject.medicory.model.medication.MedicationResponseDTO;
import com.graduationProject.medicory.model.prescription.PrescriptionRequestDTO;
import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
import com.graduationProject.medicory.model.prescription.PrescriptionResponseDTO;
import com.graduationProject.medicory.model.tests.ImagingTestRequestDTO;
import com.graduationProject.medicory.model.tests.ImagingTestResponseDTO;
import com.graduationProject.medicory.model.tests.LabTestRequestDTO;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import com.graduationProject.medicory.repository.MedicationsRepositories.MedicationRepository;
import com.graduationProject.medicory.repository.testsRepositories.ImagingTestRepository;
import com.graduationProject.medicory.repository.testsRepositories.LabTestRepository;
import com.graduationProject.medicory.repository.usersRepositories.DoctorRepository;
import com.graduationProject.medicory.repository.MedicationsRepositories.MedicineRepository;
import com.graduationProject.medicory.repository.usersRepositories.OwnerRepository;
import com.graduationProject.medicory.repository.MedicationsRepositories.PrescriptionRepository;
import com.graduationProject.medicory.service.doctor.DoctorPrescriptionService;
import com.graduationProject.medicory.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorPrescriptionServiceImpl implements DoctorPrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final MedicationMapper medicationMapper;
    private final OwnerRepository ownerRepository;
    private final MedicineRepository medicineRepository;
    private final SecurityUtils securityUtils;
    private final DoctorRepository doctorRepository;
    private final LabTestMapper labTestMapper;
    private final ImagingTestMapper imagingTestMapper;
    private final LabTestRepository labTestRepository;
    private final ImagingTestRepository imagingTestRepository;
    private final MedicationRepository medicationRepository;

    @Transactional
    @Override
    public boolean addNewPrescription(String userCode, PrescriptionRequestDTO prescriptionRequestDTO) {
        log.trace("Doctor wants to add prescriptionRequestDTO {}, for owner with id {}", prescriptionRequestDTO, userCode);
        Owner owner = ownerRepository.findByUserCode(userCode)
                .orElseThrow(() -> new RecordNotFoundException("This owner with id " + userCode + " doesn't exist"));

        Prescription newPrescription = new Prescription();
        newPrescription.setCreatedAt(LocalDateTime.now());
        newPrescription.setUpdatedAt(LocalDateTime.now());
        newPrescription.setOwner(owner);
        newPrescription.setDoctor(getCurrentDoctor());


        prescriptionRepository.save(newPrescription);

        List<Medication> medications = mapMedications(prescriptionRequestDTO.getMedications(), owner, newPrescription);
        List<LabTest> labTests = mapLabTests(prescriptionRequestDTO.getLabTests(), owner, newPrescription);
        List<ImagingTest> imagingTests = mapImagingTests(prescriptionRequestDTO.getImagingTests(), owner, newPrescription);

        newPrescription.setMedications(medications);
        newPrescription.setImagingTests(imagingTests);
        newPrescription.setMedicationStatus(true);

        if (medications.size() > 0) {
            newPrescription.setPharmacyNeeded(true);
        }
        if (labTests.size() > 0 || imagingTests.size() > 0) {
            newPrescription.setLabNeeded(true);
        }


        labTestRepository.saveAll(labTests);
        imagingTestRepository.saveAll(imagingTests);
        medicationRepository.saveAll(medications);
        prescriptionRepository.save(newPrescription);

        log.info("Prescription Added successfully!");
        return true;
    }

    private List<Medication> mapMedications(List<MedicationDTO> medicationDTOs, Owner owner, Prescription prescription) {
        return medicationDTOs.stream()
                .map(medicationRequestDTO -> {
                    Medicine medicine = getMedicineByName(medicationRequestDTO.getMedicineName());
                    Medication medication = medicationMapper.toEntity(medicationRequestDTO);
                    medication.setPrescription(prescription);
                    medication.setCreatedAt(LocalDateTime.now());
                    medication.setUpdatedAt(LocalDateTime.now());
                    medication.setOwner(owner);
                    medication.setMedicine(medicine);
                    log.info("Medication ... {}", medication);
                    return medication;
                })
                .collect(Collectors.toList());
    }

    private List<LabTest> mapLabTests(List<LabTestRequestDTO> labTestDTOs, Owner owner, Prescription prescription) {
        return labTestDTOs.stream()
                .map(labTestRequestDTO -> {
                    LabTest labTest = labTestMapper.toEntity(labTestRequestDTO);
                    labTest.setPrescription(prescription);
                    labTest.setCreatedAt(LocalDateTime.now());
                    labTest.setUpdatedAt(LocalDateTime.now());
                    labTest.setOwner(owner);
                    log.info("lab .... {} ", labTest);
                    return labTest;
                })
                .collect(Collectors.toList());
    }

    private List<ImagingTest> mapImagingTests(List<ImagingTestRequestDTO> imagingTestDTOs, Owner owner, Prescription prescription) {
        return imagingTestDTOs.stream()
                .map(imagingTestRequestDTO -> {
                    ImagingTest imagingTest = imagingTestMapper.toEntity(imagingTestRequestDTO);
                    imagingTest.setPrescription(prescription);
                    imagingTest.setCreatedAt(LocalDateTime.now());
                    imagingTest.setUpdatedAt(LocalDateTime.now());
                    imagingTest.setOwner(owner);
                    return imagingTest;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PrescriptionResponseDTO findPrescriptionById(Long prescriptionId) {
        log.info("Doctor want to find Prescription By Id {}", prescriptionId);

        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() ->
                        new RecordNotFoundException("This prescription with id " + prescriptionId + " doesn't exist"));
        List<MedicationResponseDTO> medicationResponseDTOS = prescription.getMedications()
                .stream().map(medicationMapper::toDTo)
                .collect(Collectors.toList());

        List<ImagingTestResponseDTO> imagingTestResponseDTOS = prescription.getImagingTests()
                .stream().map(imagingTestMapper::toDTO)
                .collect(Collectors.toList());

        List<LabTestResponseDTO> labTestResponseDTOS = prescription.getTests()
                .stream().map(labTestMapper::toDTO)
                .collect(Collectors.toList());
        log.info("labTestResponseDTOS {}", labTestResponseDTOS);

        PrescriptionResponse prescriptionDetails = getPrescriptionDetails(prescription);

        PrescriptionResponseDTO prescriptionResponseDTO = PrescriptionResponseDTO
                .builder()
                .medications(medicationResponseDTOS)
                .imagingTests(imagingTestResponseDTOS)
                .labTests(labTestResponseDTOS)
                .prescriptionResponse(prescriptionDetails)
                .build();

        log.trace("this the prescription {}", prescriptionResponseDTO);
        return prescriptionResponseDTO;
    }

    private PrescriptionResponse getPrescriptionDetails(Prescription prescription) {
        return PrescriptionResponse.builder()
                .prescriptionId(prescription.getId())
                //.doctorName(prescription.getDoctor().getFirstName() + prescription.getDoctor().getLastName())
                .medicationStatus(prescription.isMedicationStatus())
                .prescriptionStatus(prescription.isPrescriptionStatus())
                .createdAt(prescription.getCreatedAt())
                .updatedAt(prescription.getUpdatedAt())
                .build();
    }

    @Override
    public List<PrescriptionResponseDTO> getAllPrescriptions(String userCode) {
        log.info("Doctor wants to get all Prescriptions for owner with id {}", userCode);
        Owner owner = ownerRepository.findByUserCode(userCode)
                .orElseThrow(() -> new RecordNotFoundException("This owner with code " + userCode + " doesn't exist"));

        return owner.getPrescriptions()
                .stream()
                .map(prescription -> {
                    PrescriptionResponseDTO prescriptionResponseDTO = PrescriptionResponseDTO.builder()
                            .prescriptionResponse(mapPrescriptionResponse(prescription))
                            .medications(mapMedications(prescription.getMedications()))
                            .labTests(mapLabTests(prescription.getTests()))
                            .imagingTests(mapImagingTests(prescription.getImagingTests()))
                            .build();
                    return prescriptionResponseDTO;
                })
                .collect(Collectors.toList());
    }

    private PrescriptionResponse mapPrescriptionResponse(Prescription prescription) {
        return PrescriptionResponse.builder()
                .prescriptionId(prescription.getId())
                //.doctorName(prescription.getDoctor().getFirstName() + prescription.getDoctor())
                .medicationStatus(prescription.isMedicationStatus())
                .prescriptionStatus(prescription.isPrescriptionStatus())
                .createdAt(prescription.getCreatedAt())
                .updatedAt(prescription.getUpdatedAt())
                .build();
    }

    private List<MedicationResponseDTO> mapMedications(List<Medication> medications) {
        return medications.stream()
                .map(medicationMapper::toDTo)
                .collect(Collectors.toList());
    }

    private List<LabTestResponseDTO> mapLabTests(List<LabTest> labTests) {
        return labTests.stream()
                .map(labTestMapper::toDTO)
                .collect(Collectors.toList());
    }

    private List<ImagingTestResponseDTO> mapImagingTests(List<ImagingTest> imagingTests) {
        return imagingTests.stream()
                .map(imagingTestMapper::toDTO)
                .collect(Collectors.toList());
    }


    private Medicine getMedicineByName(String name){
        Medicine medicine = medicineRepository.findByName(name)
                .orElse(null);

        if (medicine == null){
            Medicine newMedicine = new Medicine();
            newMedicine.setName(name);
            medicineRepository.save(newMedicine);
            return newMedicine;
        }

        log.info("This medicine info: {}", medicine);
        return medicine;
    }

    private Doctor getCurrentDoctor () {
        String currentUserEmail = securityUtils.getCurrentAuthenticatedUserEmail();
        Doctor doctor = doctorRepository.
                findDoctorByUserEmail(currentUserEmail)
                .orElse(null);
        log.info("doctor : {}", doctor);
        return doctor;
    }

}
