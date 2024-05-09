package com.graduationProject.medicory.controller.admin.users;

import com.graduationProject.medicory.model.users.clinic.ClinicDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicRequestDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicResponseDTO;
import com.graduationProject.medicory.service.admin.users.AdminClinicService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/clinics")
@RequiredArgsConstructor
public class AdminClinicController {


    private final AdminClinicService clinicService;

    @GetMapping("/code/{code}")
    public ResponseEntity<ClinicResponseDTO> findClinicByCode(@PathVariable String code) {
        ClinicResponseDTO clinic = clinicService.findClinicByUserCode(code);
        return new ResponseEntity<>(clinic, HttpStatus.OK);
    }

    @GetMapping("/email/{userEmail}")
    public ResponseEntity<ClinicResponseDTO> findClinicByUserEmail(@PathVariable String userEmail) {
        ClinicResponseDTO clinic = clinicService.findClinicByUserEmail(userEmail);
        return new ResponseEntity<>(clinic, HttpStatus.OK);
    }

    @GetMapping("/name/{clinicName}")
    public ResponseEntity<List<ClinicResponseDTO>> findClinicByClinicName(@PathVariable String clinicName) {
        List<ClinicResponseDTO> clinics = clinicService.findClinicsByName(clinicName);
        return new ResponseEntity<>(clinics, HttpStatus.OK);
    }

    @GetMapping("/id/{clinicId}/clinic")
    public ResponseEntity<ClinicDTO> getAllDataOfClinicById(@PathVariable long clinicId) {
       ClinicDTO clinic = clinicService.showAllDataOfClinicByClinicId(clinicId);
       return new ResponseEntity<>(clinic,HttpStatus.OK);
    }

    @PostMapping("/clinic")
    public ResponseEntity<String> addClinic(@RequestBody ClinicRequestDTO newClinic){
        String message = clinicService.addNewClinic(newClinic);
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }
    @PutMapping("/id/{clinicId}/clinic")
    public ResponseEntity<String> updateClinic(@PathVariable long clinicId,@RequestBody ClinicDTO updatedClinic){
        String message = clinicService.updateClinic(updatedClinic,clinicId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @DeleteMapping("/id/{clinicId}")
    public ResponseEntity<String> deleteClinic(@PathVariable long clinicId){
        String  message = clinicService.deleteClinicById(clinicId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

}
