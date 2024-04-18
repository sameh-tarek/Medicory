package com.sameh.medicory.service.admin.impl;

import com.sameh.medicory.entity.usersEntities.Admin;
import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.exception.ConflictException;
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

import java.time.LocalDate;
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
        Admin admin = adminRepository.findByUserEmail(email);
        if(admin !=null){
           return adminMapper.toDTO(admin);
        }throw new RecordNotFoundException("No admin with this email");

    }
    //TODO with fName and sName
    @Override
    public List<AdminDTO> getAdminByName(String fName, String lName) {
        return null;
    }

    @Override
    public String addAdmin(AdminDTO newAdmin) {
         Admin admin = adminMapper.toEntity(newAdmin);
         User user = admin.getUser();
         User existing =userRepository.findByEmail(user.getEmail());
         if(existing == null){
             user.setCreatedAt(LocalDate.now());
             user.setUpdatedAt(LocalDate.now());
             userRepository.save(user);
             adminRepository.save(admin);
             return  "Admin added sucessfully";
         }throw new ConflictException("User Email "+user.getEmail()+" already exist");


    }

    @Override
    public String updateAdmin(AdminDTO updatedAdminDTO, Long adminId) {
        if(adminId>0){
            Optional<Admin> optionalAdmin=adminRepository.findById(adminId);
            if(optionalAdmin.isPresent()){
                Admin oldAdmin=optionalAdmin.get();
                oldAdmin.setFirstName(updatedAdminDTO.getFirstName());
                oldAdmin.setLastName(updatedAdminDTO.getLastName());
                oldAdmin.setMaritalStatus(updatedAdminDTO.getMaritalStatus());
                oldAdmin.setGender(updatedAdminDTO.getGender());

                User updatedUser =userMapper.toEntity(updatedAdminDTO.getUser());

                if(updatedUser !=null){
                    User oldUser = oldAdmin.getUser();
                    oldUser.setEmail(updatedUser.getEmail());
                    oldUser.setPassword(updatedUser.getPassword());
                    oldUser.setEnabled(updatedUser.isEnabled());
                    oldUser.setRole(updatedUser.getRole());
                    oldUser.setUpdatedAt(LocalDate.now());
                    userRepository.save(oldUser);

                }
                adminRepository.save(oldAdmin);
                return "Admin updatd sucessfully";

            }throw new RecordNotFoundException("No Admin in this id "+adminId);

        }throw new IllegalArgumentException("Invalid id "+adminId);
    }

    @Override
    public String deleteAdmin(Long adminId) {
     if(adminId>0){
         Optional<Admin> opAdmin=adminRepository.findById(adminId);
         if(opAdmin.isPresent()){
             Admin admin = opAdmin.get();
             adminRepository.deleteById(adminId);
             userRepository.deleteById(admin.getUser().getId());
             return "deleted sucessfully";
         }
         throw new RecordNotFoundException("No admin with this id "+adminId );
     }
     throw new IllegalArgumentException("Invalid id "+adminId);
    }
}
