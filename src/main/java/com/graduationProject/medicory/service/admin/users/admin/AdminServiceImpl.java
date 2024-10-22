package com.graduationProject.medicory.service.admin.users.admin;

import com.graduationProject.medicory.entity.phoneEntities.UserPhoneNumber;
import com.graduationProject.medicory.entity.usersEntities.Admin;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.exception.ConflictException;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.exception.UserDisabledException;
import com.graduationProject.medicory.mapper.usersMappers.AdminMapper;
import com.graduationProject.medicory.model.users.admin.AdminDTO;
import com.graduationProject.medicory.model.users.admin.AdminRequestDTO;
import com.graduationProject.medicory.model.users.admin.AdminResponseDTO;
import com.graduationProject.medicory.repository.phoneRepositories.UserPhoneNumberRepository;
import com.graduationProject.medicory.repository.usersRepositories.AdminRepository;
import com.graduationProject.medicory.repository.usersRepositories.UserRepository;
import com.graduationProject.medicory.utils.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final UserPhoneNumberRepository userPhoneRepo;
    private final PasswordEncoder passwordEncoder;
    private final PasswordGenerator passwordGenerator;


    @Override
    public AdminDTO showAllAdminDataById(Long adminId) {
        log.info("Fetching data of admin with id {}", adminId);
        if (adminId > 0) {
            Admin admin = adminRepository.findById(adminId)
                    .orElseThrow(() -> new RecordNotFoundException("No admin with id " + adminId));
            AdminDTO response = adminMapper.toDTO(admin);
            response.setPassword(null);
            return response;
        }
        throw new IllegalArgumentException("Invalid id " + adminId);
    }

    @Override
    public AdminResponseDTO findAdminByEmail(String email) {
        log.info("Searching for admin with email {}", email);
        Admin admin = adminRepository.findByUserEmail(email)
                .orElseThrow(() -> new RecordNotFoundException("No admin with email " + email));
        return adminMapper.toResponseDTO(admin);
    }

    @Override
    public List<AdminResponseDTO> findAdminByName(String fullName) {
        log.info("Searching for admins with name {}", fullName);
        if (fullName != null && !fullName.isBlank()) {
            List<Admin> admins;
            if (fullName.contains(" ")) {
                String[] nameParts = fullName.split(" ");
                String fName = nameParts[0];
                String lName = nameParts[1];
                admins = adminRepository.findAdminsByFirstNameAndLastName(fName, lName);
            } else {
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
        log.info("Searching for admin with code {}", userCode);
        Admin admin = adminRepository.findByUserCode(userCode)
                .orElseThrow(() -> new RecordNotFoundException("No admin with code " + userCode));
        return adminMapper.toResponseDTO(admin);
    }

    @Override
    public String addAdmin(AdminRequestDTO newAdmin) {
        log.info("Adding new admin");
        Admin admin = adminMapper.toRequestEntity(newAdmin);
        User newUser = admin.getUser();
        Optional<User> existing = userRepository.findByEmail(newUser.getEmail());
        if (!existing.isPresent()) {
            String password = passwordGenerator.generatePassword();
            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());
            newUser.setPassword(passwordEncoder.encode(password));

            List<UserPhoneNumber> userPhoneNumbers = newUser.getUserPhoneNumbers()
                    .stream()
                    .map(userPhoneNumber -> {
                        Optional<UserPhoneNumber> user = userPhoneRepo.findUserByPhone(userPhoneNumber.getPhone());
                        if (user.isPresent())
                            throw new ConflictException("This phone number " + userPhoneNumber.getPhone() + " already exists");

                        userPhoneNumber.setUser(newUser);
                        return userPhoneNumber;
                    })
                    .collect(Collectors.toList());
            userRepository.save(newUser);
            adminRepository.save(admin);
            userPhoneRepo.saveAll(userPhoneNumbers);
            passwordGenerator.sendPasswordEmail(newUser.getEmail(), password);
            log.info("Admin added successfully");
            return "Admin added successfully";
        }
        throw new ConflictException("User Email " + newUser.getEmail() + " already exists");
    }

    @Override
    public String updateAdmin(AdminDTO updatedAdminDTO, Long adminId) {
        log.info("Updating admin with id: {}", adminId);
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

        oldUser.setEmail(updatedAdminDTO.getEmail());
        oldUser.setEnabled(updatedAdminDTO.isEnabled());
        oldUser.setRole(updatedAdminDTO.getRole());
        oldUser.setUpdatedAt(LocalDateTime.now());

        if (updatedAdminDTO.getPassword() != null) {
            String newPassword = updatedAdminDTO.getPassword();
            oldUser.setPassword(passwordEncoder.encode(newPassword));
            passwordGenerator.sendPasswordEmail(updatedAdminDTO.getEmail(), newPassword);
        }
        userRepository.save(oldUser);
        adminRepository.save(oldAdmin);
        userPhoneRepo.saveAll(oldUserPhoneNumbers);
        log.info("Admin with id {} updated successfully", adminId);
        return "Admin updated successfully";
    }


    @Override
    public String deleteAdmin(Long adminId) {
        log.info("Deleting admin with id: {}", adminId);
        if (adminId > 0) {
            Admin admin = adminRepository
                    .findById(adminId)
                    .orElseThrow(() -> new RecordNotFoundException("NO admin with id " + adminId));
            User unEnabledUser = admin.getUser();
            if (unEnabledUser.isEnabled()) {
                unEnabledUser.setEnabled(false);
                unEnabledUser.setUpdatedAt(LocalDateTime.now());
                userRepository.save(unEnabledUser);
                log.info("Admin with id {} deleted", adminId);
                return "admin deleted ";
            }
            log.error("User with id {} already disabled", adminId);
            throw new UserDisabledException("This user is unEnabled already");
        }
        log.error("Invalid id {}", adminId);
        throw new IllegalArgumentException("Invalid id " + adminId);
    }

}
