package com.graduationProject.medicory.controller.pharmacy;

import com.graduationProject.medicory.model.medication.CurrentScheduleRequest;
import com.graduationProject.medicory.model.medication.MedicationDTO;
import com.graduationProject.medicory.service.pharmacy.PharmacyCurrentScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("pharmacy/prescriptions/")
public class PharmacyCurrentScheduleController {
    private final PharmacyCurrentScheduleService pharmacyCurrentScheduleService;

    @GetMapping("{userCode}/{prescriptionId}/medications")
    ResponseEntity<List<MedicationDTO>> getMedicationsOfPrescription(@PathVariable String userCode, @PathVariable Long prescriptionId){
        List<MedicationDTO> response = pharmacyCurrentScheduleService.getMedicationOfPrescription(userCode,prescriptionId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
    @PostMapping("{userCode}/medications")
    ResponseEntity<String> addToCurrentSchedule(@PathVariable String userCode, @Valid @RequestBody CurrentScheduleRequest currentScheduleRequest){
        String response = pharmacyCurrentScheduleService.createTreatmentSchedule(userCode, currentScheduleRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("{userCode}/medications/{medicationId}")
    ResponseEntity<String> createVoiceRecord(@PathVariable String userCode, @RequestParam MultipartFile file, @PathVariable Long medicationId) throws IOException {
        String response = pharmacyCurrentScheduleService.createVoiceRecord( userCode, file, medicationId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);

    }

    @DeleteMapping("{userCode}/medications/{medicationId}/records/{recordId}")
    ResponseEntity<String> deleteVoiceRecord(@PathVariable String userCode, @PathVariable Long medicationId, @PathVariable Long recordId) throws IOException {
        String response = pharmacyCurrentScheduleService.deleteVoiceRecord( userCode, medicationId, recordId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);

    }


}