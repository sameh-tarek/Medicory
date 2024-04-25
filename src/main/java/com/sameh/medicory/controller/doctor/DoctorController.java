package com.sameh.medicory.controller.doctor;

import com.sameh.medicory.model.patient.PatientPersonalInformation;
import com.sameh.medicory.service.doctor.DoctorService;
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
