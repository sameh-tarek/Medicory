package com.graduationProject.medicory.service.emergrncy;

import com.graduationProject.medicory.model.emergency.EmergencyDTO;


public interface EmergencyService {
    EmergencyDTO getEmergencyInfo(Long ownerId);

}
