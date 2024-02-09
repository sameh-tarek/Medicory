package com.sameh.medicory.controller;

import com.sameh.medicory.model.patient.PatientPersonalInformation;
import com.sameh.medicory.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping("/patient-personal-information")
    public ResponseEntity<PatientPersonalInformation> getPatientPersonalInformation(){
        PatientPersonalInformation result = doctorService.getPatientPersonalInformation();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
