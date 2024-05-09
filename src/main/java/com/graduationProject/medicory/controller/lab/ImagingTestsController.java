package com.graduationProject.medicory.controller.lab;

import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
import com.graduationProject.medicory.model.tests.ImagingTestResponseDTO;
import com.graduationProject.medicory.service.lab.imageTest.LabImageTestService;
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
@RequestMapping("/lab/imagingTest/")
public class ImagingTestsController {
    final private LabImageTestService labImageTestService;

    @GetMapping("{prescriptionId}")
    ResponseEntity<List<ImagingTestResponseDTO>> getAllImagingTests(@PathVariable Long prescriptionId){
        List<ImagingTestResponseDTO> response = labImageTestService.getAllImageTestsOfPrescription(prescriptionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("active/{prescriptionId}")
    ResponseEntity<List<ImagingTestResponseDTO>> getActiveImagingTests(@PathVariable Long prescriptionId){
        List<ImagingTestResponseDTO> response = labImageTestService.getActiveImageTestsOfPrescription(prescriptionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
