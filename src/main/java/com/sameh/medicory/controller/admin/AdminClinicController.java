package com.sameh.medicory.controller.admin;

import com.sameh.medicory.model.users.ClinicDTO;
import com.sameh.medicory.service.admin.AdminClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/clinics")
@RequiredArgsConstructor
public class AdminClinicController {


   private final AdminClinicService clinicService;

    @GetMapping("/clinicId/{clinicId}")
    public ClinicDTO findClinicById(@PathVariable Long clinicId){

        return clinicService.findClinicById(clinicId);
    }


    @GetMapping("/clinicName/{clinicName}")
    public List<ClinicDTO> findClinicByName(@PathVariable String clinicName){
        return clinicService.findClinicsByName(clinicName);
    }
    @GetMapping("/clinicEmail/{email}")
    public ClinicDTO findClinicByEmail(@PathVariable String email){
        return clinicService.findClinicByUserEmail(email);
    }
    @GetMapping("")
    public List<ClinicDTO> showAllClinics(){
       return clinicService.getAllClinics();
    }
    @PostMapping("/clinic")
    public String insertNewClinic(@RequestBody ClinicDTO newClinic){
       return clinicService.addNewClinic(newClinic);
    }
    @PutMapping("/{clinicId}/changedClinic")
    public String updateClinic(@PathVariable Long clinicId ,@RequestBody ClinicDTO clinic){

        return clinicService.updateClinic(clinic,clinicId);

    }
    @DeleteMapping("/{clinicId}")
    public String deleteClinic(@PathVariable Long clinicId ){
        return clinicService.deleteClinicById(clinicId);
    }
}
