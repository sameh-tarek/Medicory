package com.sameh.medicory.controller.doctor;

import com.sameh.medicory.model.labtests.LabTestDTO;
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
    public ResponseEntity<List<LabTestDTO>> getAllLabTestsForPatient(@PathVariable Long ownerId) {
        List<LabTestDTO> labTests = doctorService.findAllLabTestsForPatient(ownerId);
        return ResponseEntity.ok(labTests);
    }

    @DeleteMapping("tests/{testId}")
    public ResponseEntity<String> deleteLabTestFromHistory(@PathVariable(name = "testId") Long testId){
        return new ResponseEntity<>(doctorService.deleteLabTestFromHistory(testId), HttpStatus.OK);
    }
}
