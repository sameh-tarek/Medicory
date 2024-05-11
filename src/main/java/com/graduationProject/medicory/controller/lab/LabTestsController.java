package com.graduationProject.medicory.controller.lab;

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

    @GetMapping("/{prescriptionId}")
    ResponseEntity<List<LabTestResponseDTO>> getAllTests(@PathVariable Long prescriptionId){
        List<LabTestResponseDTO> response = labTestService.getAllTestsOfPrescription(prescriptionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    //*
    @GetMapping("/active/{prescriptionId}")
    ResponseEntity<List<LabTestResponseDTO>> getActiveTests(@PathVariable Long prescriptionId){
        List<LabTestResponseDTO> response = labTestService.getActiveTestsOfPrescription(prescriptionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
