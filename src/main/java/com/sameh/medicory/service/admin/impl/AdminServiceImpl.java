package com.sameh.medicory.service.admin.impl;

import com.sameh.medicory.entity.usersEntities.Admin;
import com.sameh.medicory.exception.RecordNotFoundException;
import com.sameh.medicory.mapper.AdminMapper;
import com.sameh.medicory.mapper.UserMapper;
import com.sameh.medicory.model.users.AdminDTO;
import com.sameh.medicory.repository.AdminRepository;
import com.sameh.medicory.repository.UserRepository;
import com.sameh.medicory.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
   private  final AdminRepository adminRepository;
   private final UserRepository userRepository;
   private final AdminMapper adminMapper;
   private final UserMapper  userMapper;
    @Override
    public List<AdminDTO> showAllAdmins() {
       List<Admin> allAdmins= adminRepository.findAll();
       if(!allAdmins.isEmpty()){
           return adminMapper.toDTOs(allAdmins);
       }
       throw  new RecordNotFoundException("No admins in the system");
    }

    @Override
    public AdminDTO getAdminById(Long adminId) {
        if(adminId>0){
            Optional<Admin> optAdmin=adminRepository.findById(adminId);
            if(optAdmin.isPresent()){
                Admin admin =optAdmin.get();
                return adminMapper.toDTO(admin);
            }
            throw new RecordNotFoundException("No Admin with this id "+adminId );
        }
        throw new IllegalArgumentException("Invalid id : "+adminId);
    }

    @Override
    public AdminDTO getAdminByEmail(String email) {
        return null;
    }
    //TODO with fName and sName
    @Override
    public List<AdminDTO> getAdminByName(String fName, String lName) {
        return null;
    }

    @Override
    public String addAdmin(AdminDTO newAdmin) {
        return null;
    }

    @Override
    public String updateAdmin(AdminDTO updatedAdminDTO, Long adminId) {
        return null;
    }

    @Override
    public String deleteAdmin(Long adminId) {
        return null;
    }
}
