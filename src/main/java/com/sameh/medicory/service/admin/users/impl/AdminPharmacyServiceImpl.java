package com.sameh.medicory.service.admin.users.impl;

import com.sameh.medicory.entity.phoneEntities.UserPhoneNumber;
import com.sameh.medicory.entity.usersEntities.Pharmacy;
import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.exception.ConflictException;
import com.sameh.medicory.exception.RecordNotFoundException;
import com.sameh.medicory.exception.UserDisabledException;
import com.sameh.medicory.mapper.PharmacyMpper;
import com.sameh.medicory.mapper.UserMapper;
import com.sameh.medicory.model.users.pharmacy.PharmacyRequestDTO;
import com.sameh.medicory.model.users.pharmacy.PharmacyResponseDTO;
import com.sameh.medicory.repository.PharmacyRepository;
import com.sameh.medicory.repository.UserPhoneNumberRepository;
import com.sameh.medicory.repository.UserRepository;
import com.sameh.medicory.service.admin.users.AdminPharmacyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//TODO logs
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminPharmacyServiceImpl implements AdminPharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final UserRepository userRepository;
    private final PharmacyMpper pharmacyMpper;
    private final UserMapper userMapper;
    private final UserPhoneNumberRepository userPhoneRepo;


    @Override
    public PharmacyResponseDTO findPharmacyByUserCode(String userCode) {
        Pharmacy pharmacy = pharmacyRepository.findByUserCode(userCode)
                .orElseThrow(() -> new RecordNotFoundException("No Pharmacy with code " + userCode));
        return pharmacyMpper.toResponseDTO(pharmacy);
    }

    @Override
    public PharmacyResponseDTO findPharmacyByUserEmail(String email) {
        Pharmacy pharmacy = pharmacyRepository.findByUserEmail(email)
                .orElseThrow(() -> new RecordNotFoundException("No User *PHARMACY* with email " + email));
        return pharmacyMpper.toResponseDTO(pharmacy);
    }

    @Override
    public List<PharmacyResponseDTO> findPharmacyByName(String pharmacyName) {
        List<Pharmacy> pharmacies = pharmacyRepository.findByName(pharmacyName);
        if (!pharmacies.isEmpty()) {
            return pharmacies.stream()
                    .map(pharmacyMpper::toResponseDTO)
                    .collect(Collectors.toList());
        }
        throw new RecordNotFoundException("No pharmacies with name " + pharmacyName);
    }

    @Override
    public PharmacyRequestDTO showAllDataOfPharmacyById(long id) {
        return null;
    }

    @Override
    public String addPharmacy(PharmacyRequestDTO newPharmacyRequestDTO) {
        Pharmacy newPharmacy = pharmacyMpper.toEntity(newPharmacyRequestDTO);
        User newUser = newPharmacy.getUser();
        Optional<User> existingUser = userRepository.findByEmail(newUser.getEmail());
        if (!existingUser.isPresent()) {

            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());

            List<UserPhoneNumber> userPhoneNumbers = newUser.getUserPhoneNumbers()
                    .stream()
                    .map(userPhoneNumber -> {
                        User user = userPhoneRepo.findUserByPhone(userPhoneNumber.getPhone())
                                .orElseThrow(() -> new ConflictException("Phone number" + userPhoneNumber.getPhone() + "already exist"));
                        userPhoneNumber.setUser(newUser);
                        return userPhoneNumber;
                    })
                    .collect(Collectors.toList());

            userRepository.save(newUser);
            pharmacyRepository.save(newPharmacy);
            userPhoneRepo.saveAll(userPhoneNumbers);
            return "Pharmacy inserted sucessfully";
        }
        throw new ConflictException("User email " + newUser.getEmail() + " already exist");
    }

    @Override
    public String updatePharmacy(PharmacyRequestDTO updatedPharmacy, Long pharmacyId) {
        if (pharmacyId > 0) {
            Pharmacy oldPharmacy = pharmacyRepository.findById(pharmacyId)
                    .orElseThrow(() -> new RecordNotFoundException("No pharmacy with id " + pharmacyId));
            oldPharmacy.setName(updatedPharmacy.getName());
            oldPharmacy.setAddress(updatedPharmacy.getAddress());
            oldPharmacy.setOwnerName(updatedPharmacy.getOwnerName());
            oldPharmacy.setGoogleMapsLink(updatedPharmacy.getGoogleMapsLink());

            User updatedUser = userMapper.toEntity(updatedPharmacy.getUser());
            User oldUser = oldPharmacy.getUser();

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
                            existingPhoneNumber.setPhone(updatedPhoneNumber.getPhone());
                        }
                        return existingPhoneNumber;
                    })
                    .collect(Collectors.toList());

            userRepository.save(oldUser);
            pharmacyRepository.save(oldPharmacy);
            userPhoneRepo.saveAll(existingUserPhoneNumbers);
            return "Pharmacy updated sucessfully";

        }
        throw new IllegalArgumentException("Invalid id " + pharmacyId);
    }

    @Override
    public String deletePharmacy(Long pharmacyId) {
        if (pharmacyId > 0) {
            Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId)
                    .orElseThrow(() -> new RecordNotFoundException("No pharmacy with id " + pharmacyId));

            User unEnabledUser = pharmacy.getUser();
            if (unEnabledUser.isEnabled()) {
                unEnabledUser.setUpdatedAt(LocalDateTime.now());
                unEnabledUser.setEnabled(false);

                userRepository.save(unEnabledUser);
                return "deleted sucessfully";
            }
            throw new UserDisabledException("This user is unEnabled already");
        }
        throw new IllegalArgumentException("Invalid id " + pharmacyId);
    }
}
