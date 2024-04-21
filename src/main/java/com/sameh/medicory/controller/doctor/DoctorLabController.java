package com.sameh.medicory.controller.doctor;

import com.sameh.medicory.model.tests.LabTestDTO;
import com.sameh.medicory.service.doctor.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor/patient")
@RequiredArgsConstructor
public class DoctorLabController {

    private final DoctorService doctorService;

    @GetMapping("/{ownerId}/tests")
    public List<LabTestDTO> getAllLabTestsForPatient(@PathVariable Long ownerId) {
        return doctorService.findAllLabTestsForPatient(ownerId);
    }

    @DeleteMapping("tests/{testId}")
    public String deleteLabTestFromHistory(@PathVariable(name = "testId") Long testId){
        return doctorService.deleteLabTestFromHistory(testId);
    }
}
