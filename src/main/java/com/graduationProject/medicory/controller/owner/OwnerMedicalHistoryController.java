package com.graduationProject.medicory.controller.owner;

import com.graduationProject.medicory.model.allergies.AllergiesResponseDTO;
import com.graduationProject.medicory.model.chronicDisease.ChronicDiseasesResponseDTO;
import com.graduationProject.medicory.model.immunization.ImmunizationResponseDTO;
import com.graduationProject.medicory.model.surgery.SurgeryResponseDTO;
import com.graduationProject.medicory.service.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners/{userCode}/medical-history")
public class OwnerMedicalHistoryController {

    @Autowired
    OwnerService ownerService;

    @GetMapping("/chronic-diseases")
    public List<ChronicDiseasesResponseDTO> getOwnerAllChronicDiseases(@PathVariable String userCode){
        return ownerService.getOwnerChronicDiseases(userCode);
    }

    @GetMapping("/chronic-diseases/{diseaseId}")
    public ChronicDiseasesResponseDTO getOwnerChronicDisease(@PathVariable long diseaseId, @PathVariable String userCode){
        return ownerService.getOwnerChronicDiseaseById(diseaseId, userCode);
    }

    @GetMapping("/allergies")
    public List<AllergiesResponseDTO> getOwnerAllergies(@PathVariable String userCode){
        return ownerService.getOwnerAllergies(userCode);
    }

    @GetMapping("/allergies/{diseaseId}")
    public AllergiesResponseDTO getOwnerAllergy(@PathVariable long diseaseId, @PathVariable String userCode){
        return ownerService.getOwnerAllergyById(diseaseId, userCode);
    }

    @GetMapping("/immunizations")
    public List<ImmunizationResponseDTO> getOwnerImmunizations(@PathVariable String userCode){
        return ownerService.getOwnerImmunizations(userCode);
    }

    @GetMapping("/immunizations/{diseaseId}")
    public ImmunizationResponseDTO getOwnerImmunization(@PathVariable long diseaseId, @PathVariable String userCode){
        return ownerService.getOwnerImmunizationById(diseaseId, userCode);
    }

    @GetMapping("/surgeries")
    public List<SurgeryResponseDTO> getOwnerSurgeries(@PathVariable String userCode){
        return ownerService.getOwnerSurgeries(userCode);
    }

    @GetMapping("/surgeries/{diseaseId}")
    public SurgeryResponseDTO getOwnerSurgeries(@PathVariable long diseaseId, @PathVariable String userCode){
        return ownerService.getOwnerSurgeryById(diseaseId, userCode);
    }
}
