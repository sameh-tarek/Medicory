package com.sameh.medicory.controller.admin;

import com.sameh.medicory.model.users.ClinicDTO;
import com.sameh.medicory.service.admin.AdminClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/clinic")
public class AdminClinicController {

    @Autowired
   private  AdminClinicService clinicService;

    @GetMapping("/clinicById/{clinicId}")
    public ClinicDTO findClinicById(@PathVariable Long clinicId){

        return clinicService.findClinicById(clinicId);
    }

    //TODO ? entity or dto

    @GetMapping("/clinicByName/{clinicName}")
    public List<ClinicDTO> findClinicByName(@PathVariable String clinicName){
        return clinicService.findClinicsByName(clinicName);
    }
    @GetMapping("/clinicByEmail/{email}")
    public ClinicDTO findClinicByEmail(@PathVariable String email){
        return clinicService.findClinicByUserEmail(email);
    }
    @GetMapping("/allClinics")
    public List<ClinicDTO> showAllClinics(){
       return clinicService.getAllClinics();
    }
    @PostMapping
    public String insertNewClinic(@RequestBody ClinicDTO newClinic){
       return clinicService.addNewClinic(newClinic);
    }
    @PutMapping("/{clinicId}")
    public String updateClinic(@PathVariable Long clinicId ,@RequestBody ClinicDTO clinic){

        return clinicService.updateClinic(clinic,clinicId);

    }
    @DeleteMapping("/{clinicId}")
    public String deleteClinic(@PathVariable Long clinicId ){
        return clinicService.deleteClinicById(clinicId);
    }
}
