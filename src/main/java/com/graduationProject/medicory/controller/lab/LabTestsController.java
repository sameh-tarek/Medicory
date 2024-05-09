package com.graduationProject.medicory.controller.lab;

import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import com.graduationProject.medicory.service.lab.test.LabTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lab/tests")
public class LabTestsController {
    private final LabTestService labTestService;
    @GetMapping("/{userCode}/prescriptions")
    ResponseEntity<List<PrescriptionResponse>> getAllPrescriptionsHaveTests(@PathVariable String userCode){
        List<PrescriptionResponse> response = labTestService.getAllPrescriptionsHaveTests(userCode);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{userCode}/active-prescriptions")
    ResponseEntity<List<PrescriptionResponse>> getActivePrescriptionsHaveTests(@PathVariable String userCode){
        List<PrescriptionResponse> response = labTestService.getActivePrescriptionsHaveTests(userCode);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{prescriptionId}")
    ResponseEntity<List<LabTestResponseDTO>> getAllTests(@PathVariable Long prescriptionId){
        List<LabTestResponseDTO> response = labTestService.getAllTestsOfPrescription(prescriptionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    //*
    @GetMapping("/active-tests/{prescriptionId}")
    ResponseEntity<List<LabTestResponseDTO>> getActiveTests(@PathVariable Long prescriptionId){
        List<LabTestResponseDTO> response = labTestService.getActiveTests(prescriptionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
