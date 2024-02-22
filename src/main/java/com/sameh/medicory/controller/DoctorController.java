package com.sameh.medicory.controller;

import com.sameh.medicory.model.chronicDisease.ChronicDiseasesDTO;
import com.sameh.medicory.model.patient.PatientPersonalInformation;
import com.sameh.medicory.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/patient/personal-information")
    public ResponseEntity<PatientPersonalInformation> getPatientPersonalInformation(){
        PatientPersonalInformation result = doctorService.getPatientPersonalInformation();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/patient/chronic-diseases")
    public ResponseEntity<List<ChronicDiseasesDTO>> getPatientChronicDiseases(){
        List<ChronicDiseasesDTO> result = doctorService.getPatientChronicDiseases();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
