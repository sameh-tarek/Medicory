package com.graduationProject.medicory.controller.owner;

import com.graduationProject.medicory.model.users.clinic.ClinicSearchResponseDTO;
import com.graduationProject.medicory.model.users.hospital.HospitalSearchResponseDTO;
import com.graduationProject.medicory.model.users.lab.LabSearchResponseDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacySearchResponseDTO;
import com.graduationProject.medicory.service.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/owners/search")
public class OwnerSearchController {
    @Autowired
    private OwnerService ownerService;

    @GetMapping("/labs")
    public List<LabSearchResponseDTO> getAllLabs(){
        return ownerService.getAllLabs();
    }

    @GetMapping("/labs/{labName}")
    public List<LabSearchResponseDTO> getLabsByName(@PathVariable String labName){
        return ownerService.getLabsByName(labName);
    }

    @GetMapping("/pharmacies")
    public List<PharmacySearchResponseDTO> getAllPharmacies(){
        return ownerService.getAllPharmacies();
    }

    @GetMapping("/pharmacies/{pharmacyName}")
    public List<PharmacySearchResponseDTO> getPharmaciesByName(@PathVariable String pharmacyName){
        return ownerService.getPharmaciesByName(pharmacyName);
    }

    @GetMapping("/hospitals")
    public List<HospitalSearchResponseDTO> getAllHospitals(){
        return ownerService.getAllHospitals();
    }

    @GetMapping("/hospitals/{hospitalName}")
    public List<HospitalSearchResponseDTO> getHospitalsByName(@PathVariable String hospitalName){
        return ownerService.getHospitalsByName(hospitalName);
    }

    @GetMapping("/clinics")
    public List<ClinicSearchResponseDTO> getAllClinics(){
        return ownerService.getAllClinics();
    }

    @GetMapping("/clinics/{clinicName}")
    public List<ClinicSearchResponseDTO> getClinicsByName(@PathVariable String clinicName){
        return ownerService.getClinicsByName(clinicName);
    }

    @GetMapping("/clinics/names/{doctorName}")
    public List<ClinicSearchResponseDTO> getClinicsByDoctorName(@PathVariable String doctorName){
        return ownerService.getClinicsByDoctorName(doctorName);
    }



}
