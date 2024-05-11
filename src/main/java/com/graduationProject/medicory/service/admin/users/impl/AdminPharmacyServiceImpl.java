package com.graduationProject.medicory.service.admin.users.impl;

import com.graduationProject.medicory.entity.phoneEntities.UserPhoneNumber;
import com.graduationProject.medicory.entity.usersEntities.Pharmacy;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.exception.ConflictException;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.exception.UserDisabledException;
import com.graduationProject.medicory.mapper.PharmacyMpper;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyRequestDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyResponseDTO;
import com.graduationProject.medicory.repository.PharmacyRepository;
import com.graduationProject.medicory.repository.UserPhoneNumberRepository;
import com.graduationProject.medicory.repository.UserRepository;
import com.graduationProject.medicory.service.admin.users.AdminPharmacyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminPharmacyServiceImpl implements AdminPharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final UserRepository userRepository;
    private final PharmacyMpper pharmacyMpper;
    private final UserPhoneNumberRepository userPhoneRepo;


    @Override
    public PharmacyResponseDTO findPharmacyByUserCode(String userCode) {
        log.info("Searching for pharmacy with code {}", userCode);
        Pharmacy pharmacy = pharmacyRepository.findByUserCode(userCode)
                .orElseThrow(() -> new RecordNotFoundException("No Pharmacy with code " + userCode));
        return pharmacyMpper.toResponseDTO(pharmacy);
    }

    @Override
    public PharmacyResponseDTO findPharmacyByUserEmail(String email) {
        log.info("Searching for pharmacy with email {}", email);
        Pharmacy pharmacy = pharmacyRepository.findByUserEmail(email)
                .orElseThrow(() -> new RecordNotFoundException("No User *PHARMACY* with email " + email));
        return pharmacyMpper.toResponseDTO(pharmacy);
    }

    @Override
    public List<PharmacyResponseDTO> findPharmacyByName(String pharmacyName) {
        log.info("Searching for pharmacies with name {}", pharmacyName);
        List<Pharmacy> pharmacies = pharmacyRepository.findByName(pharmacyName);
        if (!pharmacies.isEmpty()) {
            return pharmacies.stream()
                    .map(pharmacyMpper::toResponseDTO)
                    .collect(Collectors.toList());
        }
        throw new RecordNotFoundException("No pharmacies found with name " + pharmacyName);
    }

    @Override
    public PharmacyDTO showAllDataOfPharmacyById(long id) {
        log.info("Fetching data of pharmacy with id {}", id);
        if (id > 0) {
            Pharmacy pharmacy = pharmacyRepository.findById(id)
                    .orElseThrow(() -> new RecordNotFoundException("No pharmacy with id " + id));
            return pharmacyMpper.toDTO(pharmacy);
        }
        throw new IllegalArgumentException("Invalid pharmacy id " + id);
    }

    @Override
    public String addPharmacy(PharmacyRequestDTO newPharmacyDTO) {
        log.info("Adding new pharmacy");
        Pharmacy newPharmacy = pharmacyMpper.toRequestEntity(newPharmacyDTO);
        User newUser = newPharmacy.getUser();
        Optional<User> existingUser = userRepository.findByEmail(newUser.getEmail());
        if (!existingUser.isPresent()) {

            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());

            List<UserPhoneNumber> userPhoneNumbers = newUser.getUserPhoneNumbers()
                    .stream()
                    .map(userPhoneNumber -> {
                        Optional<UserPhoneNumber> user = userPhoneRepo.findUserByPhone(userPhoneNumber.getPhone());
                        if (user.isPresent()) {
                            log.error("Admin add phone number {} already exist",userPhoneNumber.getPhone());
                            throw new ConflictException("This phone number " + userPhoneNumber.getPhone() + " already exists");
                        }
                        userPhoneNumber.setUser(newUser);
                        return userPhoneNumber;
                    })
                    .collect(Collectors.toList());

            userRepository.save(newUser);
            pharmacyRepository.save(newPharmacy);
            userPhoneRepo.saveAll(userPhoneNumbers);
            log.info("Pharmacy inserted successfully");
            return "Pharmacy inserted successfully";
        }
        throw new ConflictException("User email " + newUser.getEmail() + " already exists");
    }

    @Override
    public String updatePharmacy(PharmacyDTO updatedPharmacy, Long pharmacyId) {
        log.info("Updating pharmacy with id: {}", pharmacyId);
        if (pharmacyId > 0) {
            Pharmacy oldPharmacy = pharmacyRepository.findById(pharmacyId)
                    .orElseThrow(() -> new RecordNotFoundException("No pharmacy with id " + pharmacyId));
            oldPharmacy.setName(updatedPharmacy.getName());
            oldPharmacy.setAddress(updatedPharmacy.getAddress());
            oldPharmacy.setOwnerName(updatedPharmacy.getOwnerName());
            oldPharmacy.setGoogleMapsLink(updatedPharmacy.getGoogleMapsLink());

            User oldUser = oldPharmacy.getUser();

            oldUser.setEmail(updatedPharmacy.getEmail());
            oldUser.setPassword(updatedPharmacy.getPassword());
            oldUser.setEnabled(updatedPharmacy.isEnabled());
            oldUser.setRole(updatedPharmacy.getRole());
            oldUser.setUpdatedAt(LocalDateTime.now());

            List<String> updatedPhoneNumbers = updatedPharmacy.getUserPhoneNumbers();
            List<UserPhoneNumber> oldUserPhoneNumbers = oldUser.getUserPhoneNumbers();

            for (int i = 0; i < updatedPhoneNumbers.size(); i++) {
                String updatedPhoneNumber = updatedPhoneNumbers.get(i);
                UserPhoneNumber userPhoneNumber = oldUserPhoneNumbers.get(i);

                if (!userPhoneNumber.getPhone().equals(updatedPhoneNumber)) {
                    Optional<UserPhoneNumber> existingUser = userPhoneRepo.findUserByPhone(updatedPhoneNumber);
                    if (existingUser.isPresent()) {
                        log.error("Admin add phone number {} already exist",userPhoneNumber.getPhone());
                        throw new ConflictException("This phone number " + updatedPhoneNumber + " already exists");
                    }
                    userPhoneNumber.setPhone(updatedPhoneNumber);
                }
            }

            userRepository.save(oldUser);
            pharmacyRepository.save(oldPharmacy);
            userPhoneRepo.saveAll(oldUserPhoneNumbers);
            log.info("Pharmacy with id {} updated successfully", pharmacyId);
            return "Pharmacy updated successfully";

        }
        log.error("Invalid pharmacy id: {}", pharmacyId);
        throw new IllegalArgumentException("Invalid id: " + pharmacyId);
    }

    @Override
    public String deletePharmacy(Long pharmacyId) {
        log.info("Deleting pharmacy with id: {}", pharmacyId);
        if (pharmacyId > 0) {
            Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId)
                    .orElseThrow(() -> new RecordNotFoundException("No pharmacy with id " + pharmacyId));

            User unEnabledUser = pharmacy.getUser();
            if (unEnabledUser.isEnabled()) {
                unEnabledUser.setUpdatedAt(LocalDateTime.now());
                unEnabledUser.setEnabled(false);

                userRepository.save(unEnabledUser);
                log.info("Pharmacy with id {} deleted", pharmacyId);
                return "Deleted successfully";
            }
            log.error("User with id {} already disabled", pharmacyId);
            throw new UserDisabledException("This user is unEnabled already");
        }
        log.error("Invalid pharmacy id: {}", pharmacyId);
        throw new IllegalArgumentException("Invalid id: " + pharmacyId);
    }
}
