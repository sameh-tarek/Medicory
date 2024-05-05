package com.graduationProject.medicory.service.doctor.impl;

import com.graduationProject.medicory.entity.medicationEntities.Medication;
import com.graduationProject.medicory.entity.medicationEntities.Medicine;
import com.graduationProject.medicory.entity.medicationEntities.Prescription;
import com.graduationProject.medicory.entity.testsEntities.ImagingTest;
import com.graduationProject.medicory.entity.testsEntities.LabTest;
import com.graduationProject.medicory.entity.usersEntities.Doctor;
import com.graduationProject.medicory.entity.usersEntities.Owner;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.mapper.ImagingTestMapper;
import com.graduationProject.medicory.mapper.LabTestMapper;
import com.graduationProject.medicory.mapper.MedicationMapper;
import com.graduationProject.medicory.model.medication.MedicationDTO;
import com.graduationProject.medicory.model.medication.MedicationResponseDTO;
import com.graduationProject.medicory.model.prescription.PrescriptionRequestDTO;
import com.graduationProject.medicory.model.prescription.PrescriptionResponseDTO;
import com.graduationProject.medicory.model.tests.ImagingTestRequestDTO;
import com.graduationProject.medicory.model.tests.LabTestRequestDTO;
import com.graduationProject.medicory.repository.DoctorRepository;
import com.graduationProject.medicory.repository.MedicineRepository;
import com.graduationProject.medicory.repository.OwnerRepository;
import com.graduationProject.medicory.repository.PrescriptionRepository;
import com.graduationProject.medicory.service.doctor.DoctorPrescriptionService;
import com.graduationProject.medicory.utils.SecurityUtils;
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

    @Override
    public boolean addNewPrescription(String userCode, PrescriptionRequestDTO prescriptionRequestDTO) {
        log.trace("Doctor wants to add prescriptionRequestDTO {}, for owner with id {}", prescriptionRequestDTO, userCode);
        Owner owner = ownerRepository.findByUserCode(userCode)
                .orElseThrow(() -> new RecordNotFoundException("This owner with id " + userCode + " doesn't exist"));

        Prescription newPrescription = new Prescription();


        List<Medication> medications = mapMedications(prescriptionRequestDTO.getMedications(), owner, newPrescription);
        List<LabTest> labTests = mapLabTests(prescriptionRequestDTO.getLabTests(), owner, newPrescription);
        List<ImagingTest> imagingTests = mapImagingTests(prescriptionRequestDTO.getImagingTests(), owner, newPrescription);


        newPrescription.setMedications(medications);
        newPrescription.setLabTests(labTests);
        newPrescription.setImagingTests(imagingTests);
        newPrescription.setMedicationStatus(true);
        newPrescription.setCreatedAt(LocalDateTime.now());
        newPrescription.setUpdatedAt(LocalDateTime.now());
        newPrescription.setOwner(owner);
        newPrescription.setDoctor(getCurrentDoctor());
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
                .stream()
                .map(medicationMapper::toDTo)
                .collect(Collectors.toList());

        PrescriptionResponseDTO prescriptionResponseDTO = PrescriptionResponseDTO
                .builder()
                .medications(medicationResponseDTOS)
                .build();

        log.trace("this the prescription {}", prescriptionResponseDTO);
        return prescriptionResponseDTO;
    }

    @Override
    public List<PrescriptionResponseDTO> getAllPrescriptions(String userCode) {
        log.info("Doctor wants to get all Prescriptions for owner with id {}", userCode);
        Owner owner = ownerRepository.findByUserCode(userCode)
                .orElseThrow(() -> new RecordNotFoundException("This owner with code " + userCode + " doesn't exist"));

        return owner.getPrescriptions()
                .stream()
                .map(prescription -> {
                    PrescriptionResponseDTO prescriptionResponseDTO = PrescriptionResponseDTO.builder().build();
                    List<MedicationResponseDTO> medicationResponseDTOs = prescription.getMedications()
                            .stream()
                            .map(medication -> {
                                MedicationResponseDTO medicationResponseDTO = medicationMapper.toDTo(medication);
                                return medicationResponseDTO;
                            })
                            .collect(Collectors.toList());
                    prescriptionResponseDTO.setMedications(medicationResponseDTOs);
                    return prescriptionResponseDTO;
                })
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
