package com.graduationProject.medicory.controller.doctor;

import com.graduationProject.medicory.model.patient.PatientPersonalInformation;
import com.graduationProject.medicory.service.doctor.DoctorMedicalHistoryService;
import com.graduationProject.medicory.service.doctor.DoctorPatientPersonalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors/patients/{userCode}")
@RequiredArgsConstructor
public class DoctorPatientPersonalInfoController {

    private final DoctorPatientPersonalInfoService doctorPatientPersonalInfoService;

    @GetMapping("personal-information")
    public PatientPersonalInformation getPatientPersonalInformation(@PathVariable String userCode){
         return doctorPatientPersonalInfoService.getPatientPersonalInformation(userCode);
    }

}
