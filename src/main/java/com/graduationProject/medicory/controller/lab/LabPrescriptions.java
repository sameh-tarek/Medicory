package com.graduationProject.medicory.controller.lab;

import com.graduationProject.medicory.model.prescription.PrescriptionResponse;
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
@RequestMapping("/lab/prescriptions/")
public class LabPrescriptions {
    private final LabService labService;

    @GetMapping("{userCode}")
    ResponseEntity<List<PrescriptionResponse>> getAllPrescriptionsNeedLab(@PathVariable String userCode){
        List<PrescriptionResponse> response = labService.getAllPrescriptionsNeedLab(userCode);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("active/{userCode}")
    ResponseEntity<List<PrescriptionResponse>> getActivePrescriptionsNeedLab(@PathVariable String userCode){
        List<PrescriptionResponse> response = labService.getActivePrescriptionsNeedLab(userCode);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}