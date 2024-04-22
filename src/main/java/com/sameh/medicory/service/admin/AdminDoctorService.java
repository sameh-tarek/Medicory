package com.sameh.medicory.service.admin;

import com.sameh.medicory.model.users.DoctorDTO;

import java.util.List;

public interface AdminDoctorService {

    List<DoctorDTO> findAllDoctors();
    
    DoctorDTO findDoctorById(Long  doctorId);
    
    DoctorDTO findDoctoByUserEmail(String userEmail);
    
    List<DoctorDTO> findDoctorbyFullName(String fullName);

    String addNewDoctor(DoctorDTO newDoctorDTO);

    String updateDoctor(DoctorDTO updatedDoctorDTO ,Long doctorId);

    String deleteDoctorById(Long doctorId);
}
