package com.graduationProject.medicory.service.admin.users.impl;

import com.graduationProject.medicory.entity.phoneEntities.UserPhoneNumber;
import com.graduationProject.medicory.entity.usersEntities.Admin;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.exception.ConflictException;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.exception.UserDisabledException;
import com.graduationProject.medicory.mapper.AdminMapper;
import com.graduationProject.medicory.model.users.admin.AdminDTO;
import com.graduationProject.medicory.model.users.admin.AdminResponseDTO;
import com.graduationProject.medicory.repository.AdminRepository;
import com.graduationProject.medicory.repository.UserPhoneNumberRepository;
import com.graduationProject.medicory.repository.UserRepository;
import com.graduationProject.medicory.service.admin.users.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final AdminMapper adminMapper;
    private final UserPhoneNumberRepository userPhoneRepo;


    @Override
    public AdminDTO showAllAdminDataById(Long adminId) {
        if (adminId > 0) {
            Admin admin = adminRepository.findById(adminId)
                    .orElseThrow(() -> new RecordNotFoundException("No admin with id " + adminId));
            return adminMapper.toDTO(admin);
        }
        throw new IllegalArgumentException("Invalid id " + adminId);
    }

    @Override
    public AdminResponseDTO findAdminByEmail(String email) {
        Admin admin = adminRepository.findByUserEmail(email)
                .orElseThrow(() -> new RecordNotFoundException("No admin with email " + email));
        return adminMapper.toResponseDTO(admin);
    }

    @Override
    public List<AdminResponseDTO> findAdminByName(String fullName) {

        if (fullName != null || fullName.isBlank()) {
            List<Admin> admins = null;
            // if enter fname + lname
            if (fullName.contains(" ")) {
                String[] nameParts = fullName.split(" ");
                String fName = nameParts[0];
                String lName = nameParts[1];
                admins = adminRepository.findAdminsByFirstNameAndLastName(fName, lName);
            } else {
                // if enter only fname
                admins = adminRepository.findAdminsByFirstName(fullName);
                admins.addAll(adminRepository.findAdminsByLastName(fullName));
            }

            if (admins.isEmpty())
                throw new RecordNotFoundException("No admins with name " + fullName);

            return admins.stream()
                    .map(adminMapper::toResponseDTO)
                    .collect(Collectors.toList());

        }
        throw new IllegalArgumentException("Invalid name ");
    }

    @Override
    public AdminResponseDTO findAdminByUserCode(String userCode) {
        Admin admin = adminRepository.findByUserCode(userCode)
                .orElseThrow(() -> new RecordNotFoundException("No admin with code " + userCode));
        return adminMapper.toResponseDTO(admin);
    }

    @Override
    public String addAdmin(AdminDTO newAdmin) {
        Admin admin = adminMapper.toRequestEntity(newAdmin);
        User newUser = admin.getUser();
        Optional<User> existing = userRepository.findByEmail(newUser.getEmail());
        if (!existing.isPresent()) {
            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());

            List<UserPhoneNumber> userPhoneNumbers = newUser.getUserPhoneNumbers()
                    .stream()
                    .map(userPhoneNumber -> {
                        Optional<UserPhoneNumber> user = userPhoneRepo.findUserByPhone(userPhoneNumber.getPhone());
                        if (user.isPresent())
                            throw new ConflictException("This phone number " + userPhoneNumber.getPhone() + " already exist");

                        userPhoneNumber.setUser(newUser);
                        return userPhoneNumber;
                    })
                    .collect(Collectors.toList());
            userRepository.save(newUser);
            adminRepository.save(admin);
            userPhoneRepo.saveAll(userPhoneNumbers);
            return "Admin added sucessfully";
        }
        throw new ConflictException("User Email " + newUser.getEmail() + " already exist");


    }
    @Override
    public String updateAdmin(AdminDTO updatedAdminDTO, Long adminId) {
        if (adminId <= 0) {
            throw new IllegalArgumentException("Invalid admin ID: " + adminId);
        }

        Admin oldAdmin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RecordNotFoundException("No admin with ID " + adminId));

        oldAdmin.setFirstName(updatedAdminDTO.getFirstName());
        oldAdmin.setLastName(updatedAdminDTO.getLastName());
        oldAdmin.setMaritalStatus(updatedAdminDTO.getMaritalStatus());
        oldAdmin.setGender(updatedAdminDTO.getGender());

        User oldUser = oldAdmin.getUser();

        oldUser.setEmail(updatedAdminDTO.getEmail());
        oldUser.setEnabled(updatedAdminDTO.isEnabled());
        oldUser.setRole(updatedAdminDTO.getRole());
        oldUser.setUpdatedAt(LocalDateTime.now());

        List<String> updatedPhoneNumbers = updatedAdminDTO.getUserPhoneNumbers();
        List<UserPhoneNumber> oldUserPhoneNumbers = oldUser.getUserPhoneNumbers();

        for (int i = 0; i < updatedPhoneNumbers.size(); i++) {
            String updatedPhoneNumber = updatedPhoneNumbers.get(i);
            UserPhoneNumber userPhoneNumber = oldUserPhoneNumbers.get(i);

            if (!userPhoneNumber.getPhone().equals(updatedPhoneNumber)) {
                Optional<UserPhoneNumber> existingUser = userPhoneRepo.findUserByPhone(updatedPhoneNumber);
                if (existingUser.isPresent()) {
                    throw new ConflictException("This phone number " + updatedPhoneNumber + " already exists");
                }
                userPhoneNumber.setPhone(updatedPhoneNumber);
            }
        }

        userRepository.save(oldUser);
        adminRepository.save(oldAdmin);
        userPhoneRepo.saveAll(oldUserPhoneNumbers);
        return "Admin updated successfully";
    }



    @Override
    public String deleteAdmin(Long adminId) {
        if (adminId > 0) {
            Admin admin = adminRepository
                    .findById(adminId)
                    .orElseThrow(() -> new RecordNotFoundException("NO admin with id " + adminId));
            User unEnabledUser = admin.getUser();
            if (unEnabledUser.isEnabled()) {
                unEnabledUser.setEnabled(false);
                unEnabledUser.setUpdatedAt(LocalDateTime.now());
                userRepository.save(unEnabledUser);
                return "admin deleted ";
            }
            throw new UserDisabledException("This user is unEnabled already");
        }
        throw new IllegalArgumentException("Invalid id " + adminId);
    }

}
