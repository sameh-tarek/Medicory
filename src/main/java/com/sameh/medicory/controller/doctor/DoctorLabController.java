package com.sameh.medicory.controller.doctor;

import com.sameh.medicory.model.tests.LabTestRequestDTO;
import com.sameh.medicory.model.tests.LabTestResponseDTO;
import com.sameh.medicory.service.doctor.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor/patient")
@RequiredArgsConstructor
public class DoctorLabController {

    private final DoctorService doctorService;

    @GetMapping("/{ownerId}/tests")
    public List<LabTestResponseDTO> getAllLabTestsForPatient(@PathVariable Long ownerId) {
        return doctorService.findAllLabTestsForPatient(ownerId);
    }

    @DeleteMapping("tests/{testId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteLabTestFromHistory(@PathVariable(name = "testId") Long testId){
        return doctorService.deleteLabTestFromHistory(testId);
    }

    @PostMapping("{ownerId}/tests")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean addLabTestsForPatientThatRequiredNow (@PathVariable Long ownerId,
                                                         @RequestBody List<LabTestRequestDTO> requiredTests) {
        return doctorService.addLabTestsForPatientThatRequiredNow(ownerId, requiredTests);
    }

    @GetMapping("{ownerId}/tests/active")
    public List<LabTestResponseDTO> getActiveLabTests (@PathVariable Long ownerId) {
        return doctorService.getActiveLabTests(ownerId);
    }
}
