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
@RequestMapping("/owners/{userId}/medical-history")
public class OwnerMedicalHistoryController {

    @Autowired
    OwnerService ownerService;

    @GetMapping("/chronic-diseases")
    public List<ChronicDiseasesResponseDTO> getOwnerAllChronicDiseases(@PathVariable long userId){
        return ownerService.getOwnerChronicDiseases(userId);
    }

    @GetMapping("/chronic-diseases/{diseaseId}")
    public ChronicDiseasesResponseDTO getOwnerChronicDisease(@PathVariable long diseaseId, @PathVariable long userId){
        return ownerService.getOwnerChronicDiseaseById(diseaseId, userId);
    }

    @GetMapping("/allergies")
    public List<AllergiesResponseDTO> getOwnerAllergies(@PathVariable long userId){
        return ownerService.getOwnerAllergies(userId);
    }

    @GetMapping("/allergies/{diseaseId}")
    public AllergiesResponseDTO getOwnerAllergy(@PathVariable long diseaseId, @PathVariable long userId){
        return ownerService.getOwnerAllergyById(diseaseId, userId);
    }

    @GetMapping("/immunizations")
    public List<ImmunizationResponseDTO> getOwnerImmunizations(@PathVariable long userId){
        return ownerService.getOwnerImmunizations(userId);
    }

    @GetMapping("/immunizations/{diseaseId}")
    public ImmunizationResponseDTO getOwnerImmunization(@PathVariable long diseaseId, @PathVariable long userId){
        return ownerService.getOwnerImmunizationById(diseaseId, userId);
    }

    @GetMapping("/surgeries")
    public List<SurgeryResponseDTO> getOwnerSurgeries(@PathVariable long userId){
        return ownerService.getOwnerSurgeries(userId);
    }

    @GetMapping("/surgeries/{diseaseId}")
    public SurgeryResponseDTO getOwnerSurgeries(@PathVariable long diseaseId, @PathVariable long userId){
        return ownerService.getOwnerSurgeryById(diseaseId, userId);
    }
}
