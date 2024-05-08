package com.graduationProject.medicory.controller.owner;

import com.graduationProject.medicory.model.owner.OwnerDTO;
import com.graduationProject.medicory.service.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
