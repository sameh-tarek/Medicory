package com.graduationProject.medicory.controller.owner;

import com.graduationProject.medicory.model.medication.MedicationDTO;
import com.graduationProject.medicory.service.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/owners/{ownerId}/medication-schedule")
public class OwnerMedicationScheduleController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public List<MedicationDTO> getMedicationSchedule(@PathVariable long ownerId){
        return ownerService.getMedicationSchedule(ownerId);
    }

}
