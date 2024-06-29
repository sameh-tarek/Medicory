package com.graduationProject.medicory.controller.pharmacy;

import com.graduationProject.medicory.entity.medicationEntities.Medication;
import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
import com.graduationProject.medicory.service.pharmacy.PharmacyPrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pharmacy/prescriptions/")
public class PharmacyPrescriptionController {
    private final PharmacyPrescriptionService pharmacyPrescriptionService;
    @GetMapping("{userCode}")
    ResponseEntity<List<PrescriptionResponse>> getAllPrescriptionNeedPharmacy(@PathVariable String userCode){
        List<PrescriptionResponse> allPrescriptionsResponse = pharmacyPrescriptionService.getAllPrescriptionNeedPharmacy(userCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allPrescriptionsResponse);
    }
    @GetMapping("active/{userCode}")
    ResponseEntity<List<PrescriptionResponse>> getActivePrescriptionNeedPharmacy(@PathVariable String userCode){
        List<PrescriptionResponse> allPrescriptionsResponse = pharmacyPrescriptionService.getActivePrescriptionNeedPharmacy(userCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allPrescriptionsResponse);
    }
    @GetMapping("{userCode}/{id}")
    ResponseEntity<PrescriptionResponse> getPrescriptionById(@PathVariable String userCode, @PathVariable Long id){
        PrescriptionResponse prescription = pharmacyPrescriptionService.getPrescriptionById(userCode,id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(prescription);
    }



}