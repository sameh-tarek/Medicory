package com.graduationProject.medicory.service.admin.users;

import com.graduationProject.medicory.model.users.admin.AdminDTO;
import com.graduationProject.medicory.model.users.admin.AdminRequestDTO;
import com.graduationProject.medicory.model.users.admin.AdminResponseDTO;

import java.util.List;

public interface AdminService {
    AdminDTO showAllAdminDataById(Long adminId);
    AdminResponseDTO findAdminByEmail(String email);
    List<AdminResponseDTO> findAdminByName(String fullName);
    AdminResponseDTO  findAdminByUserCode(String userCode);
    String addAdmin(AdminRequestDTO newAdmin);
    String updateAdmin(AdminDTO updatedAdminDTO, Long adminId);
    String deleteAdmin(Long adminId);
}
