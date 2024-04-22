package com.sameh.medicory.service.emergrncy;

import com.sameh.medicory.model.emergency.EmergencyDTO;
import org.springframework.stereotype.Service;


public interface EmergencyService {
    EmergencyDTO getEmergencyInfo(Long ownerId);

}
