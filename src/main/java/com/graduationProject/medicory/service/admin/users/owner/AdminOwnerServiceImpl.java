package com.graduationProject.medicory.service.admin.users.owner;

import com.graduationProject.medicory.entity.phoneEntities.RelativePhoneNumber;
import com.graduationProject.medicory.entity.phoneEntities.UserPhoneNumber;
import com.graduationProject.medicory.entity.usersEntities.Owner;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.exception.ConflictException;
import com.graduationProject.medicory.exception.RecordNotFoundException;
import com.graduationProject.medicory.exception.UserDisabledException;
import com.graduationProject.medicory.mapper.usersMappers.OwnerMapper;
import com.graduationProject.medicory.mapper.usersMappers.UserMapper;
import com.graduationProject.medicory.model.users.owner.OwnerRequestDTO;
import com.graduationProject.medicory.model.users.owner.OwnerResponseDTO;
import com.graduationProject.medicory.repository.phoneRepositories.RelativePhoneNumberRepository;
import com.graduationProject.medicory.repository.phoneRepositories.UserPhoneNumberRepository;
import com.graduationProject.medicory.repository.usersRepositories.OwnerRepository;
import com.graduationProject.medicory.repository.usersRepositories.UserRepository;
import com.graduationProject.medicory.utils.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final PasswordGenerator passwordGenerator;


    @Override
    public List<OwnerResponseDTO> findOwnersByOwnerName(String fullName) {
        log.info("Searching for owners with name {}", fullName);
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
            throw new RecordNotFoundException("No owners with name " + fullName + " found");

        return owners.stream()
                .map(ownerMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OwnerResponseDTO findOwnerByOwnerEmail(String userEmail) {
        log.info("Searching for owner with email {}", userEmail);
        Owner owner = ownerRepository.findOwnerByUserEmail(userEmail)
                .orElseThrow(() -> new RecordNotFoundException("No user *Owner* with email " + userEmail));
        return ownerMapper.toResponseDTO(owner);

    }

    @Override
    public OwnerResponseDTO findOwnerByCode(String ownerCode) {
        log.info("Searching for owner with code {}", ownerCode);
        Owner owner = ownerRepository
                .findByUserCode(ownerCode)
                .orElseThrow(() -> new RecordNotFoundException("Owner with code " + ownerCode + " doesn't exist"));
        return ownerMapper.toResponseDTO(owner);

    }

    @Override
    public OwnerRequestDTO showAllDataOfOwnerById(long ownerId) {
        log.info("Fetching data of owner with id {}", ownerId);
        if (ownerId > 0) {
            Owner owner = ownerRepository.findById(ownerId)
                    .orElseThrow(() -> new RecordNotFoundException("No owner with id " + ownerId));
            OwnerRequestDTO response = ownerMapper.toRequestDTO(owner);
            response.getUser().setPassword(null);
            return response;
        }
        throw new IllegalArgumentException("Invalid id " + ownerId + " ......");
    }

    @Override
    public String addNewOwner(OwnerRequestDTO newOwnerDTO) {
        log.info("Adding new owner");
        Owner newOwner = ownerMapper.toEntity(newOwnerDTO);
        User newUser = newOwner.getUser();
        Optional<User> checkUserExisting = userRepository.findByEmail(newOwner.getUser().getEmail());
        if (!checkUserExisting.isPresent()) {
            String password = passwordGenerator.generatePassword();
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());

            // user phone numbers
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
            ownerRepository.save(newOwner);
            userPhoneRepo.saveAll(userPhoneNumbers);
            passwordGenerator.sendPasswordEmail(newUser.getEmail(), password);
            return "owner added successfully";
        }
        throw new ConflictException("Owner with email " + newUser.getEmail() + " already exists");
    }

    @Override
    public String updateOwner(long ownerId, OwnerRequestDTO updatedOwnerDTO) {
        log.info("Updating owner with id: {}", ownerId);
        if (ownerId > 0) {
            Owner oldOwner = ownerRepository.findById(ownerId)
                    .orElseThrow(() -> new RecordNotFoundException("No owner with id " + ownerId));
            User oldUser = oldOwner.getUser();
            Owner updatedOwner = ownerMapper.toEntity(updatedOwnerDTO);
            User updatedUser = userMapper.toEntity(updatedOwnerDTO.getUser());

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
                        Optional<RelativePhoneNumber> matchingUpdatedPhoneNumber = updatedRelativePhoneNumbers
                                .stream()
                                .filter(updatedPhoneNumber ->
                                        updatedPhoneNumber.getOwner().getId() == existingPhoneNumber.getOwner().getId())
                                .findFirst();
                        if (matchingUpdatedPhoneNumber.isPresent()) {
                            System.out.println("true");
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
                        Optional<UserPhoneNumber> matchingUpdatedPhoneNumber = updatedUserPhoneNumbers
                                .stream()
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
            if (updatedUser != null) {
                oldUser.setEmail(updatedUser.getEmail());
                oldUser.setEnabled(updatedUser.isEnabled());
                oldUser.setRole(updatedUser.getRole());
                oldUser.setUpdatedAt(LocalDateTime.now());
                if (updatedUser.getPassword() != null) {
                    String updatedPassword = updatedUser.getPassword();
                    oldUser.setPassword(updatedPassword);
                    passwordGenerator.sendPasswordEmail(updatedUser.getEmail(), updatedPassword);

                }
            }
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
        log.info("Deleting owner with id: {}", id);
        if (id > 0) {
            Owner owner = ownerRepository.findById(id)
                    .orElseThrow(() -> new RecordNotFoundException("No owner with id " + id));
            User user = owner.getUser();
            if (user.isEnabled()) {
                user.setUpdatedAt(LocalDateTime.now());
                user.setEnabled(false);
                userRepository.save(user);
                return "deleted successfully";
            }
            throw new UserDisabledException("This user is unEnabled already");
        }
        throw new IllegalArgumentException("Invalid id " + id);
    }
}

