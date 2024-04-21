package com.sameh.medicory.controller.owner;

import com.sameh.medicory.model.medication.MedicationScheduleDTO;
import com.sameh.medicory.service.owner.OwnerService;
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
    public List<MedicationScheduleDTO> getMedicationSchedule(@PathVariable long ownerId){
        return ownerService.getMedicationSchedule(ownerId);
    }

}
