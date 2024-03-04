package com.sameh.medicory.controller;

import com.sameh.medicory.model.AllergiesDTO;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesDTO;
import com.sameh.medicory.model.patient.PatientPersonalInformation;
import com.sameh.medicory.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/patient/chronic-diseases/add")
    public ResponseEntity<String> addNewChronicDiseasesForPatient(@RequestBody ChronicDiseasesDTO chronicDiseasesDTO){
       return new ResponseEntity<>(doctorService.addNewChronicDiseasesForPatient(chronicDiseasesDTO), HttpStatus.CREATED);
    }

    @GetMapping("patient/allergies")
    public ResponseEntity<List<AllergiesDTO>> getPatientAllergies(){
        List<AllergiesDTO> result = doctorService.getPatientAllergies();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
