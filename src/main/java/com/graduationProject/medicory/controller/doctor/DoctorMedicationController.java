package com.graduationProject.medicory.controller.doctor;

import com.graduationProject.medicory.model.medication.MedicationDTO;
import com.graduationProject.medicory.model.medication.MedicationResponseDTO;
import com.graduationProject.medicory.service.doctor.DoctorMedicationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors/patients")
@RequiredArgsConstructor
public class DoctorMedicationController {

    private final DoctorMedicationService medicationService;

    @GetMapping("/{userCode}/medications")
    @Operation(summary = "Get all medications History for a patient")
    public List<MedicationResponseDTO> getAllMedicationsForPatient(@PathVariable String userCode) {
        return medicationService.getAllMedicationsForPatient(userCode);
    }

    @GetMapping("/medications/{prescriptionId}")
    @Operation(summary = "Get all Prescription medications for a patient")
    public List<MedicationResponseDTO> getAllPrescriptionMedicationsForPatient(@PathVariable Long prescriptionId) {
        return medicationService.getAllPrescriptionMedicationsForPatient(prescriptionId);
    }

    @PostMapping("/{userCode}/medications")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add medications for a patient")
    public boolean addMedicationsForPatient(@PathVariable String userCode,
                                            @Valid @RequestBody List<MedicationDTO> medications,
                                            @RequestParam Long prescriptionId) {
        return medicationService.addMedicationsForPatient(userCode, medications, prescriptionId);
    }

    @GetMapping("/medications")
    @Operation(summary = "Find medication by ID")
    public MedicationResponseDTO findMedicationById(@RequestParam Long medicationId) {
        return medicationService.findMedicationById(medicationId);
    }

    @PutMapping("/medications/{medicationId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update medication")
    public boolean updateMedication(@PathVariable Long medicationId, @Valid @RequestBody MedicationDTO medicationDTO) {
        return medicationService.updateMedication(medicationId, medicationDTO);
    }

    @DeleteMapping("/medications/{medicationId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Delete medication")
    public boolean deleteMedication(@PathVariable Long medicationId) {
        return medicationService.deleteMedication(medicationId);
    }
}
