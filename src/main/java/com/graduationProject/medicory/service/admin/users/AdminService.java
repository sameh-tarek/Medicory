package com.graduationProject.medicory.service.admin.users;

import com.graduationProject.medicory.model.users.admin.AdminRequestDTO;
import com.graduationProject.medicory.model.users.admin.AdminResponseDTO;

import java.util.List;

public interface AdminService {
    AdminRequestDTO showAllAdminDataById(Long adminId);
    AdminResponseDTO findAdminByEmail(String email);
    List<AdminResponseDTO> findAdminByName(String fullName);
    AdminResponseDTO  findAdminByUserCode(String userCode);
    String addAdmin(AdminRequestDTO newAdmin);
    String updateAdmin(AdminRequestDTO updatedAdminRequestDTO, Long adminId);
    String deleteAdmin(Long adminId);
}
