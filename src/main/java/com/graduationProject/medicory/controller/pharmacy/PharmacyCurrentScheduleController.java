package com.graduationProject.medicory.controller.pharmacy;

import com.graduationProject.medicory.model.medication.CurrentScheduleRequest;
import com.graduationProject.medicory.model.medication.MedicationDTO;
import com.graduationProject.medicory.service.pharmacy.PharmacyCurrentScheduleService;
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

    @GetMapping("{userCode}/prescriptions/{prescriptionId}/medications")
    ResponseEntity<List<MedicationDTO>> getMedicationsOfPrescription(@PathVariable String userCode, @PathVariable Long prescriptionId){
        List<MedicationDTO> response = pharmacyCurrentScheduleService.getMedicationOfPrescription(userCode,prescriptionId);
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
//    @PostMapping("{userCode}/prescriptions/medications/{medicationId}")
//    ResponseEntity<String> createVoiceRecord(@PathVariable String userCode, @RequestParam MultipartFile file, @PathVariable Long medicationId){
//        String response = pharmacyCurrentScheduleService.createVoiceRecord( file, medicationId);
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(response);
//
//    }


}
