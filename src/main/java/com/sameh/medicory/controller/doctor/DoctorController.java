package com.sameh.medicory.controller.doctor;

import com.sameh.medicory.model.AllergiesDTO;
import com.sameh.medicory.model.ImmunizationDTO;
import com.sameh.medicory.model.SurgeryDTO;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesDTO;
import com.sameh.medicory.model.labtests.LabTestDTO;
import com.sameh.medicory.model.labtests.LabTestRequiredDTO;
import com.sameh.medicory.model.patient.PatientPersonalInformation;
import com.sameh.medicory.service.doctor.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/patient/personal-information")
    public ResponseEntity<PatientPersonalInformation> getPatientPersonalInformation(){
        PatientPersonalInformation result = doctorService.getPatientPersonalInformation();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/patient/chronic-diseases")
    public ResponseEntity<List<ChronicDiseasesDTO>> getPatientChronicDiseases(){
        List<ChronicDiseasesDTO> result = doctorService.getPatientChronicDiseases();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/patient/chronic-diseases")
    public ResponseEntity<String> addNewChronicDiseasesForPatient(@RequestBody ChronicDiseasesDTO chronicDiseasesDTO){
       return new ResponseEntity<>(doctorService.addNewChronicDiseasesForPatient(chronicDiseasesDTO), HttpStatus.CREATED);
    }

    @GetMapping("patient/allergies")
    public ResponseEntity<List<AllergiesDTO>> getPatientAllergies(){
        List<AllergiesDTO> result = doctorService.getPatientAllergies();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("patient/allergies")
    public ResponseEntity<String> addNewAllergiesForPatient(@RequestBody AllergiesDTO allergiesDTO) {
        return new ResponseEntity<>(doctorService.addNewAllergiesForPatient(allergiesDTO), HttpStatus.CREATED);
    }

    @GetMapping("patient/immunizations")
    public ResponseEntity<List<ImmunizationDTO>> getaAllPatientImmunizations(){
        List<ImmunizationDTO> result = doctorService.getaAllPatientImmunizations();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("patient/immunizations")
    public ResponseEntity<String> addNewImmunizationForPatient(@RequestBody ImmunizationDTO immunizationDTO) {
        return new ResponseEntity<>(doctorService.addNewImmunizationForPatient(immunizationDTO), HttpStatus.CREATED);
    }

    @GetMapping("patient/surgeries")
    public ResponseEntity<List<SurgeryDTO>> getPatientSurgicalHistory(){
        List<SurgeryDTO> result = doctorService.getPatientSurgicalHistory();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("patient/surgeries")
    public ResponseEntity<String> addNewSurgeryForPatient(@RequestBody SurgeryDTO surgeryDTO) {
        return new ResponseEntity<>(doctorService.addNewSurgeryForPatient(surgeryDTO), HttpStatus.CREATED);
    }

    @GetMapping("patient/tests")
    public ResponseEntity<List<LabTestDTO>> getAllLabTestsForPatient() {
        List<LabTestDTO> labTests = doctorService.findAllLabTestsForPatient();
        return ResponseEntity.ok(labTests);
    }

    @DeleteMapping("patient/tests/{testId}")
    public ResponseEntity<String> deleteLabTestFromHistory(@PathVariable(name = "testId") Long testId){
        return new ResponseEntity<>(doctorService.deleteLabTestFromHistory(testId), HttpStatus.OK);
    }

}
