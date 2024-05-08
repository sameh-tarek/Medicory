package com.graduationProject.medicory.service.admin.users.impl;

import com.graduationProject.medicory.entity.phoneEntities.RelativePhoneNumber;
import com.graduationProject.medicory.entity.phoneEntities.UserPhoneNumber;
import com.graduationProject.medicory.entity.usersEntities.Owner;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.exception.ConflictException;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.exception.UserDisabledException;
import com.graduationProject.medicory.mapper.OwnerMapper;
import com.graduationProject.medicory.mapper.UserMapper;
import com.graduationProject.medicory.model.users.owner.OwnerRequestDTO;
import com.graduationProject.medicory.model.users.owner.OwnerResponseDTO;
import com.graduationProject.medicory.repository.OwnerRepository;
import com.graduationProject.medicory.repository.RelativePhoneNumberRepository;
import com.graduationProject.medicory.repository.UserPhoneNumberRepository;
import com.graduationProject.medicory.repository.UserRepository;
import com.graduationProject.medicory.service.admin.users.AdminOwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminOwnerServiceImpl implements AdminOwnerService {

    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;
    private final OwnerMapper ownerMapper;
    private final UserMapper userMapper;
    private final RelativePhoneNumberRepository relativeRepo;
    private final UserPhoneNumberRepository userPhoneRepo;

    @Override
    public List<OwnerResponseDTO> findOwnersByOwnerName(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            return Collections.emptyList();
        }
        List<Owner> owners = null;
        if (fullName.contains(" ")) {
            String[] nameParts = fullName.split(" ");
            if (nameParts.length == 1) {
                // fname
                owners = ownerRepository.findOwnerByFirstName(nameParts[0]);
                owners.addAll(ownerRepository.findOwnerByMiddleName(nameParts[0]));
                owners.addAll(ownerRepository.findOwnerByLastName(nameParts[0]));

            } else if (nameParts.length == 2) {
                owners = ownerRepository.findOwnerByFirstNameAndMiddleName(nameParts[0], nameParts[1]);
                if (owners.isEmpty()) {
                    owners = ownerRepository.findOwnerByFirstNameAndLastName(nameParts[0], nameParts[1]);
                    if (owners.isEmpty())
                        owners = ownerRepository.findOwnerByMiddleNameAndLastName(nameParts[0], nameParts[1]);
                }
            } else if (nameParts.length == 3) {
                owners = ownerRepository.findOwnerByFirstNameAndMiddleNameAndLastName(nameParts[0], nameParts[1], nameParts[2]);
            }
        } else {
            owners = ownerRepository.findOwnerByFirstName(fullName);
            owners.addAll(ownerRepository.findOwnerByMiddleName(fullName));
            owners.addAll(ownerRepository.findOwnerByLastName(fullName));
        }
        if (owners.isEmpty())
            throw new RecordNotFoundException("No owners with name " + fullName + " founded :)");

        return owners.stream()
                .map(ownerMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OwnerResponseDTO findOwnerByOwnerEmail(String userEmail) {
        Owner owner = ownerRepository.findOwnerByUserEmail(userEmail)
                .orElseThrow(() -> new RecordNotFoundException("No user *Owner* with email " + userEmail));
        return ownerMapper.toResponseDTO(owner);

    }

    @Override
    public OwnerResponseDTO findOwnerByCode(String ownerCode) {

        Owner owner = ownerRepository
                .findByUserCode(ownerCode)
                .orElseThrow(() -> new RecordNotFoundException("Owner with code " + ownerCode + "doesn't exist"));
        return ownerMapper.toResponseDTO(owner);

    }

    @Override
    public OwnerRequestDTO showAllDataOfOwnerById(long ownerId) {
        if (ownerId > 0) {
            Owner owner = ownerRepository.findById(ownerId)
                    .orElseThrow(() -> new RecordNotFoundException("No owner with id " + ownerId));
            return ownerMapper.toRequestDTO(owner);
        }
        throw new IllegalArgumentException("Invalid id " + ownerId + " ......");
    }

    // TODO GENERATE CODE WITH EACH USER
    @Override
    public String addNewOwner(OwnerRequestDTO newOwnerDTO) {
        Owner newOwner = ownerMapper.toEntity(newOwnerDTO);
        User newUser = newOwner.getUser();
        Optional<User> checkUserExisting = userRepository.findByEmail(newOwner.getUser().getEmail());
        if (!checkUserExisting.isPresent()) {

            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());

            // user phone numbers
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
            ownerRepository.save(newOwner);
            userPhoneRepo.saveAll(userPhoneNumbers);
            return "owner added successfully";
        }
        throw new ConflictException("Owner with email " + newUser.getEmail() + " already exsist");
    }

    @Override
    public String updateOwner(long ownerId, OwnerRequestDTO updatedOwnerDTO) {
        if (ownerId > 0) {
            Owner oldOwner = ownerRepository.findById(ownerId)
                    .orElseThrow(() -> new RecordNotFoundException("No owner with id " + ownerId));
            User oldUser = oldOwner.getUser();
            Owner updatedOwner = ownerMapper.toEntity(updatedOwnerDTO);
            User updatedUser = userMapper.toEntity(updatedOwnerDTO.getUser());

            if (updatedUser != null) {
                oldUser.setEmail(updatedUser.getEmail());
                oldUser.setPassword(updatedUser.getPassword());
                oldUser.setEnabled(updatedUser.isEnabled());
                oldUser.setRole(updatedUser.getRole());
                oldUser.setUpdatedAt(LocalDateTime.now());
            }

            oldOwner.setFirstName(updatedOwnerDTO.getFirstName());
            oldOwner.setMiddleName(updatedOwnerDTO.getMiddleName());
            oldOwner.setLastName(updatedOwnerDTO.getLastName());
            oldOwner.setGender(updatedOwnerDTO.getGender());
            oldOwner.setDateOfBirth(updatedOwnerDTO.getDateOfBirth());
            oldOwner.setAddress(updatedOwnerDTO.getAddress());
            oldOwner.setBloodType(updatedOwnerDTO.getBloodType());
            oldOwner.setNationalId(updatedOwnerDTO.getNationalId());
            oldOwner.setMaritalStatus(updatedOwnerDTO.getMaritalStatus());
            oldOwner.setJob(updatedOwnerDTO.getJob());

            // Update or add relative phone numbers
            List<RelativePhoneNumber> updatedRelativePhoneNumbers = updatedOwner.getRelativePhoneNumbers();
            List<RelativePhoneNumber> existingRelativePhoneNumbers = oldOwner.getRelativePhoneNumbers()
                    .stream()
                    .map(existingPhoneNumber -> {
                        Optional<RelativePhoneNumber> matchingUpdatedPhoneNumber = updatedRelativePhoneNumbers.stream()
                                .filter(updatedPhoneNumber ->
                                        updatedPhoneNumber.getId() == existingPhoneNumber.getId())
                                .findFirst();

                        if (matchingUpdatedPhoneNumber.isPresent()) {
                            RelativePhoneNumber updatedPhoneNumber = matchingUpdatedPhoneNumber.get();
                            existingPhoneNumber.setPhone(updatedPhoneNumber.getPhone());
                            existingPhoneNumber.setRelation(updatedPhoneNumber.getRelation());
                        }
                        return existingPhoneNumber;
                    })
                    .collect(Collectors.toList());

            // Update or add user phone numbers
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
                            // updated !
                            if (!existingPhoneNumber.getPhone().equals(updatedPhoneNumber.getPhone())) {
                                Optional<UserPhoneNumber> existingUser = userPhoneRepo.findUserByPhone(updatedPhoneNumber.getPhone());
                                if (existingUser.isPresent()) {
                                    throw new ConflictException("This phone number " + updatedPhoneNumber.getPhone() + " already exists");
                                }
                                existingPhoneNumber.setPhone(updatedPhoneNumber.getPhone());
                            }
                        }
                        return existingPhoneNumber;
                    })

                    .collect(Collectors.toList());
            relativeRepo.saveAll(existingRelativePhoneNumbers);
            userPhoneRepo.saveAll(existingUserPhoneNumbers);
            userRepository.save(oldUser);
            ownerRepository.save(oldOwner);
            return "Owner updated successfully";
        }
        throw new IllegalArgumentException("Invalid id");
    }

    @Override
    public String deleteOwnerById(long id) {
        if (id > 0) {
            Owner owner = ownerRepository.findById(id)
                    .orElseThrow(() -> new RecordNotFoundException("No owner with id " + id));
            User user = owner.getUser();
            if (user.isEnabled()) {
                user.setUpdatedAt(LocalDateTime.now());
                user.setEnabled(false);
                userRepository.save(user);
                return "deleted sucessfully";
            }
            throw new UserDisabledException("This user is unEnabled already");
        }
        throw new IllegalArgumentException("Invalid id " + id);
    }
}
