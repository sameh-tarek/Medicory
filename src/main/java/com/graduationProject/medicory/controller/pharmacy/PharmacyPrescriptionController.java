package com.graduationProject.medicory.controller.pharmacy;

import com.graduationProject.medicory.model.prescription.PrescriptionResponseDTO;
import com.graduationProject.medicory.service.pharmacy.PharmacyPrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pharmacy")
public class PharmacyPrescriptionController {
    private final PharmacyPrescriptionService pharmacyPrescriptionService;

    @GetMapping("{userCode}/prescriptions")
    public ResponseEntity<List<PrescriptionResponseDTO>> getAllPrescription(@PathVariable String userCode) {
        List<PrescriptionResponseDTO> allPrescriptionsResponse = pharmacyPrescriptionService.getAllPrescription(userCode);
        return ResponseEntity.status(HttpStatus.OK).body(allPrescriptionsResponse);
    }

    @GetMapping("{userCode}/active-prescriptions")
    public ResponseEntity<List<PrescriptionResponseDTO>> getActivePrescription(@PathVariable String userCode) {
        List<PrescriptionResponseDTO> allPrescriptionsResponse = pharmacyPrescriptionService.getActivePrescription(userCode);
        return ResponseEntity.status(HttpStatus.OK).body(allPrescriptionsResponse);
    }

    @GetMapping("{userCode}/prescriptions/{id}")
    public ResponseEntity<PrescriptionResponseDTO> getPrescriptionById(@PathVariable String userCode, @PathVariable Long id) {
        PrescriptionResponseDTO prescription = pharmacyPrescriptionService.getPrescriptionById(userCode, id);
        return ResponseEntity.status(HttpStatus.OK).body(prescription);
    }
}
