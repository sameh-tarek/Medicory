package com.sameh.medicory.service.admin.users.impl;

import com.sameh.medicory.entity.phoneEntities.UserPhoneNumber;
import com.sameh.medicory.entity.usersEntities.Admin;
import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.exception.ConflictException;
import com.sameh.medicory.exception.RecordNotFoundException;
import com.sameh.medicory.exception.UserDisabledException;
import com.sameh.medicory.mapper.AdminMapper;
import com.sameh.medicory.mapper.UserMapper;
import com.sameh.medicory.model.users.admin.AdminRequestDTO;
import com.sameh.medicory.model.users.admin.AdminResponseDTO;
import com.sameh.medicory.repository.AdminRepository;
import com.sameh.medicory.repository.UserPhoneNumberRepository;
import com.sameh.medicory.repository.UserRepository;
import com.sameh.medicory.service.admin.users.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private final UserMapper userMapper;
    private final UserPhoneNumberRepository userPhoneRepo;


    @Override
    public AdminRequestDTO showAllAdminDataById(Long adminId) {
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
    public String addAdmin(AdminRequestDTO newAdmin) {
        Admin admin = adminMapper.toEntity(newAdmin);
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
    public String updateAdmin(AdminRequestDTO updatedAdminRequestDTO, Long adminId) {
        if (adminId > 0) {
            Admin oldAdmin = adminRepository.findById(adminId)
                    .orElseThrow(() -> new RecordNotFoundException("No admin with id " + adminId));
            oldAdmin.setFirstName(updatedAdminRequestDTO.getFirstName());
            oldAdmin.setLastName(updatedAdminRequestDTO.getLastName());
            oldAdmin.setMaritalStatus(updatedAdminRequestDTO.getMaritalStatus());
            oldAdmin.setGender(updatedAdminRequestDTO.getGender());

            User updatedUser = userMapper.toEntity(updatedAdminRequestDTO.getUser());
            User oldUser = oldAdmin.getUser();

            if (updatedUser != null) {
                oldUser.setEmail(updatedUser.getEmail());
                oldUser.setPassword(updatedUser.getPassword());
                oldUser.setEnabled(updatedUser.isEnabled());
                oldUser.setRole(updatedUser.getRole());
                oldUser.setUpdatedAt(LocalDateTime.now());

            }
            List<UserPhoneNumber> updatedUserPhoneNumbers = updatedUser.getUserPhoneNumbers();
            List<UserPhoneNumber> existingUserPhoneNumbers = oldUser.getUserPhoneNumbers()
                    .stream()
                    .map(existingPhoneNumber -> {
                        Optional<UserPhoneNumber> matchingUpdatedPhoneNumber = updatedUserPhoneNumbers.stream()
                                .filter(updatedPhoneNumber ->
                                        updatedPhoneNumber.getId() == existingPhoneNumber.getId())
                                .findFirst();

                        if (matchingUpdatedPhoneNumber.isPresent()) {
                            UserPhoneNumber updatedPhoneNumber = matchingUpdatedPhoneNumber.get();
                            Optional<UserPhoneNumber> existingUser = userPhoneRepo.findUserByPhone(updatedPhoneNumber.getPhone());
                            if (existingUser.isPresent()) {
                                throw new ConflictException("This phone number " + updatedPhoneNumber.getPhone() + " already exists");
                            }
                            existingPhoneNumber.setPhone(updatedPhoneNumber.getPhone());
                        }
                        return existingPhoneNumber;
                    })
                    .collect(Collectors.toList());

            userRepository.save(oldUser);
            adminRepository.save(oldAdmin);
            userPhoneRepo.saveAll(existingUserPhoneNumbers);
            return "Admin updatd sucessfully";
        }
        throw new IllegalArgumentException("Invalid id " + adminId);
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
