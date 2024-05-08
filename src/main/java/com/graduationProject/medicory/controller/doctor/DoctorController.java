package com.graduationProject.medicory.controller.doctor;

import com.graduationProject.medicory.model.patient.PatientPersonalInformation;
import com.graduationProject.medicory.service.doctor.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors/patients/{userCode}")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("personal-information")
    public PatientPersonalInformation getPatientPersonalInformation(@PathVariable String userCode){
         return doctorService.getPatientPersonalInformation(userCode);
    }

}
