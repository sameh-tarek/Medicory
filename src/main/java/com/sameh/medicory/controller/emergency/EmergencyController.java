package com.sameh.medicory.controller.emergency;

import com.sameh.medicory.model.emergency.EmergencyDTO;
import com.sameh.medicory.service.emergrncy.EmergencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{ownerId}/emergency")
@RequiredArgsConstructor
public class EmergencyController {
    private final EmergencyService emergencyService;
    @GetMapping()
    public EmergencyDTO showEmergencyInfo(@PathVariable Long ownerId){

        return emergencyService.getEmergencyInfo(ownerId);

    }

}
