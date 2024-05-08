package com.graduationProject.medicory.controller.pharmacy;

import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
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
    ResponseEntity<List<PrescriptionResponse>> getAllPrescription(@PathVariable String userCode){
        List<PrescriptionResponse> allPrescriptionsResponse = pharmacyPrescriptionService.getAllPrescription(userCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allPrescriptionsResponse);
    }
    @GetMapping("{userCode}/active-prescriptions")
    ResponseEntity<List<PrescriptionResponse>> getActivePrescription(@PathVariable String userCode){
        List<PrescriptionResponse> allPrescriptionsResponse = pharmacyPrescriptionService.getActivePrescription(userCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allPrescriptionsResponse);
    }
    @GetMapping("{userCode}/prescriptions/{id}")
    ResponseEntity<PrescriptionResponse> getPrescriptionById(@PathVariable String userCode, @PathVariable Long id){
        PrescriptionResponse prescription = pharmacyPrescriptionService.getPrescriptionById(userCode,id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(prescription);
    }

}
