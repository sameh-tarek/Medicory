package com.sameh.medicory.controller.pharmacy;

import com.sameh.medicory.model.medication.CurrentScheduleRequest;
import com.sameh.medicory.model.medication.MedicationDTO;
import com.sameh.medicory.service.pharmacy.PharmacyCurrentScheduleService;
import com.sameh.medicory.service.pharmacy.PharmacyPrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("pharmacy/")
public class PharmacyCurrentScheduleController {
    private final PharmacyCurrentScheduleService pharmacyCurrentScheduleService;

    @GetMapping("{userCode}/prescriptions/{prscriptionId}/medications")
    ResponseEntity<List<MedicationDTO>> getMedicationOfPrescription(@PathVariable String userCode, @PathVariable Long prscriptionId){
        List<MedicationDTO> response = pharmacyCurrentScheduleService.getMedicationOfPrescription(userCode,prscriptionId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
    @PostMapping("{userCode}/prescriptions/medications")
    ResponseEntity<String> addToCurrentSchedule(@PathVariable String userCode, @RequestBody CurrentScheduleRequest currentScheduleRequest){
        String response = pharmacyCurrentScheduleService.createTreatmentSchedule(userCode, currentScheduleRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}
