package com.graduationProject.medicory.service.admin.users;

import com.graduationProject.medicory.model.users.doctor.DoctorDTO;
import com.graduationProject.medicory.model.users.doctor.DoctorRequestDTO;
import com.graduationProject.medicory.model.users.doctor.DoctorResponseDTO;

import java.util.List;

public interface AdminDoctorService {

    DoctorDTO showAllDoctorDataById(Long doctorId);

    DoctorResponseDTO findDoctoByUserEmail(String userEmail);

    DoctorResponseDTO findDoctorByUserCode(String userCode);

    List<DoctorResponseDTO> findDoctorbyFullName(String fullName);

    String addNewDoctor(DoctorRequestDTO newDoctorRequestDTO);

    String updateDoctor(DoctorDTO updatedDoctorDTO, Long doctorId);

    String deleteDoctorById(Long doctorId);
}
