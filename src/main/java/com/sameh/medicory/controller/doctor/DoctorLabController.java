package com.sameh.medicory.controller.doctor;

import com.sameh.medicory.model.tests.LabTestRequestDTO;
import com.sameh.medicory.model.tests.LabTestResponseDTO;
import com.sameh.medicory.service.doctor.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors/patients")
@RequiredArgsConstructor
public class DoctorLabController {

    private final DoctorService doctorService;

    @GetMapping("/{userCode}/tests")
    public List<LabTestResponseDTO> getAllLabTestsForPatient(@PathVariable String userCode) {
        return doctorService.findAllLabTestsForPatient(userCode);
    }

    @DeleteMapping("tests/{testId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteLabTestFromHistory(@PathVariable(name = "testId") Long testId){
        return doctorService.deleteLabTestFromHistory(testId);
    }

    @PostMapping("{userCode}/tests")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean addLabTestsForPatientThatRequiredNow (@PathVariable String userCode,
                                                         @RequestBody List<LabTestRequestDTO> requiredTests) {
        return doctorService.addLabTestsForPatientThatRequiredNow(userCode, requiredTests);
    }

    @GetMapping("{userCode}/active-tests")
    public List<LabTestResponseDTO> getActiveLabTests (@PathVariable String userCode) {
        return doctorService.getActiveLabTests(userCode);
    }
}
