package com.sameh.medicory.controller.doctor;

import com.sameh.medicory.model.immunization.ImmunizationRequestDTO;
import com.sameh.medicory.model.immunization.ImmunizationResponseDTO;
import com.sameh.medicory.model.allergies.AllergiesRequestDTO;
import com.sameh.medicory.model.allergies.AllergiesResponseDTO;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesRequestDTO;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesResponseDTO;
import com.sameh.medicory.model.surgery.SurgeryRequestDTO;
import com.sameh.medicory.model.surgery.SurgeryResponseDTO;
import com.sameh.medicory.service.doctor.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor/patient")
@RequiredArgsConstructor
public class DoctorMedicalHistoryController {

    private final DoctorService doctorService;

    @GetMapping("/{ownerId}/chronic-diseases")
    public ResponseEntity<List<ChronicDiseasesResponseDTO>> getPatientChronicDiseases(@PathVariable Long ownerId){
        List<ChronicDiseasesResponseDTO> result = doctorService.getPatientChronicDiseases(ownerId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{ownerId}/chronic-diseases")
    public ResponseEntity<String> addNewChronicDiseasesForPatient(@RequestBody ChronicDiseasesRequestDTO chronicDiseasesRequestDTO, @PathVariable Long ownerId){
        return new ResponseEntity<>(doctorService.addNewChronicDiseasesForPatient(chronicDiseasesRequestDTO, ownerId), HttpStatus.CREATED);
    }

    @GetMapping("chronic-diseases/{diseasesId}")
    public ResponseEntity<ChronicDiseasesResponseDTO> findChronicDiseasesById(@PathVariable Long diseasesId){
        return new ResponseEntity<>(doctorService.findChronicDiseasesById(diseasesId), HttpStatus.OK);
    }

    @PutMapping("{ownerId}/chronic-diseases/{diseasesId}")
    public ResponseEntity<String> updateChronicDisease(@RequestBody ChronicDiseasesRequestDTO chronicDiseasesRequestDTO, @PathVariable Long diseasesId, @PathVariable Long ownerId){
        return new ResponseEntity<>(doctorService.updateChronicDisease(chronicDiseasesRequestDTO, diseasesId, ownerId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("chronic-diseases/{diseasesId}")
    public ResponseEntity<String> deleteChronicDiseases(@PathVariable Long diseasesId){
        return new ResponseEntity<>(doctorService.deleteChronicDiseases(diseasesId), HttpStatus.ACCEPTED);
    }


    @GetMapping("{ownerId}/allergies")
    public ResponseEntity<List<AllergiesResponseDTO>> getPatientAllergies(@PathVariable Long ownerId){
        List<AllergiesResponseDTO> result = doctorService.getPatientAllergies(ownerId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("{ownerId}/allergies")
    public ResponseEntity<String> addNewAllergiesForPatient(@RequestBody AllergiesRequestDTO allergiesRequestDTO, @PathVariable Long ownerId) {
        return new ResponseEntity<>(doctorService.addNewAllergiesForPatient(allergiesRequestDTO, ownerId), HttpStatus.CREATED);
    }


    @GetMapping("{ownerId}/immunizations")
    public ResponseEntity<List<ImmunizationResponseDTO>> getaAllPatientImmunizations(@PathVariable Long ownerId){
        List<ImmunizationResponseDTO> result = doctorService.getaAllPatientImmunizations(ownerId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("{ownerId}/immunizations")
    public ResponseEntity<String> addNewImmunizationForPatient(@RequestBody ImmunizationRequestDTO immunizationRequestDTO, @PathVariable Long ownerId) {
        return new ResponseEntity<>(doctorService.addNewImmunizationForPatient(immunizationRequestDTO, ownerId), HttpStatus.CREATED);
    }


    @GetMapping("{ownerId}/surgeries")
    public ResponseEntity<List<SurgeryResponseDTO>> getPatientSurgicalHistory(@PathVariable Long ownerId){
        List<SurgeryResponseDTO> result = doctorService.getPatientSurgicalHistory(ownerId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("{ownerId}/surgeries")
    public ResponseEntity<String> addNewSurgeryForPatient(@RequestBody SurgeryRequestDTO surgeryRequestDTO, @PathVariable Long ownerId) {
        return new ResponseEntity<>(doctorService.addNewSurgeryForPatient(surgeryRequestDTO, ownerId), HttpStatus.CREATED);
    }

}
