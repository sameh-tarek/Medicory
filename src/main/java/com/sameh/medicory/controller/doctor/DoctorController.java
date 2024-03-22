package com.sameh.medicory.controller.doctor;

import com.sameh.medicory.model.patient.PatientPersonalInformation;
import com.sameh.medicory.service.doctor.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor/patient/{ownerId}")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("personal-information")
    public ResponseEntity<PatientPersonalInformation> getPatientPersonalInformation(@PathVariable Long ownerId){
        PatientPersonalInformation result = doctorService.getPatientPersonalInformation(ownerId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}