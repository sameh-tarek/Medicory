package com.graduationProject.medicory.service.admin.users;

import com.graduationProject.medicory.model.users.clinic.ClinicRequestDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicResponseDTO;

import java.util.List;


public interface AdminClinicService {
    ClinicResponseDTO findClinicByUserCode(String userCode);

    List<ClinicResponseDTO> findClinicsByName(String name);

    ClinicResponseDTO findClinicByUserEmail(String userEmail);

    ClinicDTO showAllDataOfClinicByClinicId(long clinicId);

    String addNewClinic(ClinicRequestDTO clinic);

    String updateClinic(ClinicDTO clinicDTO, Long clinicId);

    String deleteClinicById(Long clinicId);

}
