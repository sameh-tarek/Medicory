package com.sameh.medicory.controller.owner;

import com.sameh.medicory.model.allergies.AllergiesResponseDTO;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesResponseDTO;
import com.sameh.medicory.model.immunization.ImmunizationResponseDTO;
import com.sameh.medicory.model.owner.OwnerDTO;
import com.sameh.medicory.model.surgery.SurgeryResponseDTO;
import com.sameh.medicory.service.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners/{userId}")
public class OwnerController {
    @Autowired
    OwnerService ownerService;

    @GetMapping("/personal-information")
    public OwnerDTO getPersonalInformation(@PathVariable long userId){
        return ownerService.getOwnerPersonalInformation(userId);
    }


}
