package com.sameh.medicory.service.admin.users;

import com.sameh.medicory.entity.usersEntities.Admin;
import com.sameh.medicory.model.users.AdminDTO;

import java.util.List;

public interface AdminService {
    List<AdminDTO> showAllAdmins();
    AdminDTO getAdminById(Long adminId);
    AdminDTO getAdminByEmail(String email);
    List<AdminDTO> getAdminByName(String fullName);
    String addAdmin(AdminDTO newAdmin);
    String updateAdmin(AdminDTO updatedAdminDTO ,Long adminId);
    String deleteAdmin(Long adminId);
}
