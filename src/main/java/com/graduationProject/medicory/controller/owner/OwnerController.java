package com.graduationProject.medicory.controller.owner;

import com.graduationProject.medicory.model.owner.OwnerResponseDTO;
import com.graduationProject.medicory.service.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owners/{userCode}")
public class OwnerController {
    @Autowired
    OwnerService ownerService;

    @GetMapping("/personal-information")
    public OwnerResponseDTO getPersonalInformation(@PathVariable String userCode){
        return ownerService.getOwnerPersonalInformation(userCode);
    }


}
