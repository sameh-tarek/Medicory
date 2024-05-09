package com.graduationProject.medicory.service.admin.users;

import com.graduationProject.medicory.model.users.hospital.HospitalRequestDTO;
import com.graduationProject.medicory.model.users.hospital.HospitalDTO;
import com.graduationProject.medicory.model.users.hospital.HospitalResponseDTO;

import java.util.List;

public interface AdminHospitalService {

    HospitalResponseDTO findHospitalByUserCode(String userCode);
    HospitalResponseDTO findHospitalByEmail(String email);
    List<HospitalResponseDTO> findHospitalByName(String name);
    HospitalDTO showAllDataOfHospitalById(long hospitalId);
    String addHospital(HospitalRequestDTO newHospital);
    String updateHospital(Long hospitalId, HospitalDTO updatedHospital);
    String deleteHospital(long hospitalId);
}
