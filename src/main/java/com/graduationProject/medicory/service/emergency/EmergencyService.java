package com.graduationProject.medicory.service.emergency;

import com.graduationProject.medicory.model.emergency.EmergencyDTO;


public interface EmergencyService {
    EmergencyDTO getEmergencyInfo(String ownerCode);

}
