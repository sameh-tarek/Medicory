package com.sameh.medicory.service.admin.users;

import com.sameh.medicory.model.users.clinic.ClinicRequestDTO;
import com.sameh.medicory.model.users.clinic.ClinicResponseDTO;

import java.util.List;


public interface AdminClinicService {
    ClinicResponseDTO findClinicByUserCode(String userCode);

    List<ClinicResponseDTO> findClinicsByName(String name);

    ClinicResponseDTO findClinicByUserEmail(String userEmail);

    ClinicRequestDTO showAllDataOfClinicByClinicId(long clinicId);

    String addNewClinic(ClinicRequestDTO clinic);

    String updateClinic(ClinicRequestDTO clinicRequestDTO, Long clinicId);

    String deleteClinicById(Long clinicId);
}
