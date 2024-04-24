package com.sameh.medicory.controller.pharmacy;

import com.sameh.medicory.entity.medicationEntities.CurrentSchedule;
import com.sameh.medicory.model.medication.CurrentScheduleRequest;
import com.sameh.medicory.model.prescription.PrescriptionResponse;
import com.sameh.medicory.service.pharmacy.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {
    private final PharmacyService pharmacyService;
    @GetMapping("{ownerId}/prescriptions")
    ResponseEntity<List<PrescriptionResponse>> getAllPrescription(@PathVariable Long ownerId){
        List<PrescriptionResponse> allPrescriptionsResponse = pharmacyService.getAllPrescription(ownerId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allPrescriptionsResponse);
    }
    @GetMapping("{ownerId}/active-prescriptions")
    ResponseEntity<List<PrescriptionResponse>> getActivePrescription(@PathVariable Long ownerId){
        List<PrescriptionResponse> allPrescriptionsResponse = pharmacyService.getActivePrescription(ownerId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allPrescriptionsResponse);
    }
    @GetMapping("/prescriptions/{id}")
    ResponseEntity<PrescriptionResponse> getPrescriptionById(@PathVariable Long id){
        PrescriptionResponse prescription = pharmacyService.getPrescriptionById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(prescription);
    }
    @PostMapping("prescriptions")
    ResponseEntity<String>addToCurrentSchedule(@RequestBody CurrentScheduleRequest currentScheduleRequest){
        String response = pharmacyService.createTreatmentSchedule(currentScheduleRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

//    @DeleteMapping("prescriptions/{id}")
//    ResponseEntity<String>addToCurrentSchedule(@RequestBody ){
//        String response = pharmacyService.createTreatmentSchedule(medicationRequestDTO);
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(response);
//    }
}
