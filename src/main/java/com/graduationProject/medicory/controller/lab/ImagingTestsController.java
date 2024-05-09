package com.graduationProject.medicory.controller.lab;

import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
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
@RequestMapping("/lab/imagingTest")
public class ImagingTestsController {
    final private LabImageTestService labImageTestService;

    @GetMapping("/{userCode}/prescriptions")
    ResponseEntity<List<PrescriptionResponse>> getAllPrescriptionsHaveImagingTests(@PathVariable String userCode){
        List<PrescriptionResponse> response = labImageTestService.getAllPrescriptionsHaveImagingTests(userCode);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{userCode}/active-prescriptions")
    ResponseEntity<List<PrescriptionResponse>> getActivePrescriptionsHaveImagingTests(@PathVariable String userCode){
        List<PrescriptionResponse> response = labImageTestService.getActivePrescriptionsHaveImagingTests(userCode);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
