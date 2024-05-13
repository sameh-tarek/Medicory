package com.graduationProject.medicory.controller.lab;

import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import com.graduationProject.medicory.service.lab.test.LabTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lab/tests/")
public class LabTestsController {

    private final LabTestService labTestService;

    @GetMapping("{prescriptionId}")
    ResponseEntity<List<LabTestResponseDTO>> getAllTests(@PathVariable Long prescriptionId){
        List<LabTestResponseDTO> response = labTestService.getAllTestsOfPrescription(prescriptionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    //*
    @GetMapping("active/{prescriptionId}")
    ResponseEntity<List<LabTestResponseDTO>> getActiveTests(@PathVariable Long prescriptionId){
        List<LabTestResponseDTO> response = labTestService.getActiveTestsOfPrescription(prescriptionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("uploadResult/{testId}")
    ResponseEntity<String> uploadTestResult(@RequestParam("file") MultipartFile file, @PathVariable Long testId) throws IOException {
        String response = labTestService.uploadTestResult(file,testId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
