package com.graduationProject.medicory.controller.lab;

import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import com.graduationProject.medicory.service.lab.LabService;
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
@RequestMapping("/lab")
public class LabController {
    private final LabService labService;
    @GetMapping("/{userCode}/prescriptions")
    ResponseEntity<List<PrescriptionResponse>> getAllPrescriptions(@PathVariable String userCode){
        List<PrescriptionResponse> response = labService.getAllPrescriptions(userCode);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{userCode}/active-prescriptions")
    ResponseEntity<List<PrescriptionResponse>> getActivePrescriptions(@PathVariable String userCode){
        List<PrescriptionResponse> response = labService.getActivePrescriptions(userCode);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/tests/{prescriptionId}")
    ResponseEntity<List<LabTestResponseDTO>> getAllTests(@PathVariable Long prescriptionId){
        List<LabTestResponseDTO> response = labService.getAllTestsOfPrescription(prescriptionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    //*
    @GetMapping("/active-tests/{prescriptionId}")
    ResponseEntity<List<LabTestResponseDTO>> getActiveTests(@PathVariable Long prescriptionId){
        List<LabTestResponseDTO> response = labService.getActiveTests(prescriptionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
