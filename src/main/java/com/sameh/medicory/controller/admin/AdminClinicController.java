package com.sameh.medicory.controller.admin;

import com.sameh.medicory.model.users.ClinicDTO;
import com.sameh.medicory.service.admin.AdminClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/clinics")
@RequiredArgsConstructor
public class AdminClinicController {


   private final AdminClinicService clinicService;

    @GetMapping("/clinicId/{clinicId}")
    public ResponseEntity< ClinicDTO> findClinicById(@PathVariable Long clinicId){
        ClinicDTO clinic = clinicService.findClinicById(clinicId);
        return ResponseEntity.ok(clinic);
    }


    @GetMapping("/clinicName/{clinicName}")
    public ResponseEntity<List<ClinicDTO>> findClinicByName(@PathVariable String clinicName){
       List<ClinicDTO> clinics= clinicService.findClinicsByName(clinicName);
        return ResponseEntity.ok(clinics);
    }
    @GetMapping("/clinicEmail/{email}")
    public ResponseEntity< ClinicDTO> findClinicByEmail(@PathVariable String email){
        ClinicDTO clinic= clinicService.findClinicByUserEmail(email);
        return ResponseEntity.ok(clinic);
    }
    @GetMapping("")
    public ResponseEntity<List<ClinicDTO>> showAllClinics(){
       List<ClinicDTO> clinics = clinicService.getAllClinics();
       return ResponseEntity.ok(clinics);
    }
    @PostMapping("/clinic")
    public ResponseEntity<String> insertNewClinic(@RequestBody ClinicDTO newClinic){
       String message= clinicService.addNewClinic(newClinic);
       return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    @PutMapping("/clinic/{clinicId}")
    public ResponseEntity< String> updateClinic(@PathVariable Long clinicId ,@RequestBody ClinicDTO clinic){
        String message= clinicService.updateClinic(clinic,clinicId);
        return ResponseEntity.ok(message);
    }
    @DeleteMapping("/clinic/{clinicId}")
    public ResponseEntity< String> deleteClinic(@PathVariable Long clinicId ){
        String message= clinicService.deleteClinicById(clinicId);
      return ResponseEntity.ok(message);
    }
}
