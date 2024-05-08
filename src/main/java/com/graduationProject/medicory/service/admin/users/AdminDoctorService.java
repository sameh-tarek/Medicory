package com.graduationProject.medicory.service.admin.users;

import com.graduationProject.medicory.model.users.doctor.DoctorRequestDTO;
import com.graduationProject.medicory.model.users.doctor.DoctorResponseDTO;

import java.util.List;

public interface AdminDoctorService {

    DoctorRequestDTO showAllDoctorDataById(Long  doctorId);
    
    DoctorResponseDTO findDoctoByUserEmail(String userEmail);
    DoctorResponseDTO findDoctorByUserCode(String userCode);
    List<DoctorResponseDTO> findDoctorbyFullName(String fullName);

    String addNewDoctor(DoctorRequestDTO newDoctorRequestDTO);

    String updateDoctor(DoctorRequestDTO updatedDoctorRequestDTO, Long doctorId);

    String deleteDoctorById(Long doctorId);
}
