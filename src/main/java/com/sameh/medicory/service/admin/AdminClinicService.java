package com.sameh.medicory.service.admin;

import com.sameh.medicory.model.users.ClinicDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdminClinicService {
    ClinicDTO findClinicById(Long clinicId);
    List<ClinicDTO> findClinicsByName(String name);
     ClinicDTO findClinicByUserEmail(String userEmail);
     List<ClinicDTO> getAllClinics();
     String addNewClinic(ClinicDTO clinic);
     String updateClinic(ClinicDTO clinicDTO ,Long clinicId);
     String deleteClinicById(Long clinicId);
}
