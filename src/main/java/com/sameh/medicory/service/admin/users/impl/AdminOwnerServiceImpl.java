package com.sameh.medicory.service.admin.users.impl;

import com.sameh.medicory.entity.usersEntities.Owner;
import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.exception.ConflictException;
import com.sameh.medicory.exception.RecordNotFoundException;
import com.sameh.medicory.exception.UserDisabledException;
import com.sameh.medicory.mapper.OwnerMapper;
import com.sameh.medicory.model.users.owner.OwnerRequestDTO;
import com.sameh.medicory.model.users.owner.OwnerResponseDTO;
import com.sameh.medicory.repository.OwnerRepository;
import com.sameh.medicory.repository.UserRepository;
import com.sameh.medicory.service.admin.users.AdminOwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminOwnerServiceImpl implements AdminOwnerService {

    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;
    private final OwnerMapper ownerMapper;

    @Override
    public List<OwnerResponseDTO> findOwnersByOwnerName(String fullName) {
        return null;
    }

    @Override
    public OwnerResponseDTO findOwnerByOwnerEmail(String userEmail) {
        Owner owner = ownerRepository.findOwnerByUserEmail(userEmail)
                .orElseThrow(()-> new RecordNotFoundException("No user *Owner* with email "+userEmail));
        return ownerMapper.toResponseDTO(owner);

    }

    @Override
    public OwnerResponseDTO findOwnerByCode(String ownerCode) {

        Owner owner = ownerRepository
                .findByUserCode(ownerCode)
                .orElseThrow(()-> new RecordNotFoundException("Owner with code "+ownerCode +"doesn't exist"));
        return ownerMapper.toResponseDTO(owner);

    }

    @Override
    public OwnerRequestDTO showAllDataOfOwnerById(long ownerId) {
        if (ownerId > 0){
          Owner owner = ownerRepository.findById(ownerId)
                  .orElseThrow(()-> new RecordNotFoundException("No owner with id "+ownerId));
          return ownerMapper.toRequestDTO(owner);
    }
      throw  new IllegalArgumentException("Invalid id "+ownerId+" ......");
    }

    // TODO GENERATE CODE WITH EACH USER
    @Override
    public String addNewOwner(OwnerRequestDTO newOwnerDTO) {
          Owner newOwner = ownerMapper.toEntity(newOwnerDTO);
          User newUser = newOwner.getUser();
        Optional< User> checkUserExisting = userRepository.findByEmail(newOwner.getUser().getEmail());
        if(!checkUserExisting.isPresent()){

         newUser.setCreatedAt(LocalDateTime.now());
         newUser.setUpdatedAt(LocalDateTime.now());
         userRepository.save(newUser);
         ownerRepository.save(newOwner);
        return "owner added successfully";}
        throw new ConflictException("Owner with email "+ newUser.getEmail()+" already exsist");
    }

    @Override
    public String updateOwner(long ownerId, OwnerRequestDTO updatedOwnerDTO) {
//        Owner oldOwner = ownerRepository.findById(ownerId)
//                .orElseThrow(()-> new RecordNotFoundException("No owner with id "+ ownerId));
//        Owner newOwner =ownerMapper.toEntity(updatedOwnerDTO);
//        User oldUser = oldOwner.getUser();
//        oldUser.setUpdatedAt(LocalDateTime.now());
//        if (!oldUser.getEmail().equals(newOwner.getUser().getEmail())) {
//            User existingUser = userRepository.findByEmail(newOwner.getUser().getEmail())
//                    .orElse(null);
//            if (existingUser != null && existingUser.getId() != oldUser.getId()) {
//                throw new ConflictException("Email " + newOwner.getUser().getEmail() + " is already in use");
//            }
//
//            oldUser.setEmail(newOwner.getUser().getEmail());
//        }
//        oldOwner.setAllergies(newOwner.getAllergies());
//        userRepository.save(oldUser);
//        ownerRepository.save(newOwner);
        return "Owner updated successfully";
    }

    @Override
    public String deleteOwnerById(long id) {
        if(id>0) {
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
        throw new IllegalArgumentException("Invalid id "+id);
    }
}
