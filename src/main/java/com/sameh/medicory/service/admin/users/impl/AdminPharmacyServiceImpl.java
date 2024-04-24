package com.sameh.medicory.service.admin.users.impl;

import com.sameh.medicory.entity.usersEntities.Pharmacy;
import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.exception.ConflictException;
import com.sameh.medicory.exception.RecordNotFoundException;
import com.sameh.medicory.mapper.PharmacyMpper;
import com.sameh.medicory.mapper.UserMapper;
import com.sameh.medicory.model.users.PharmacyDTO;
import com.sameh.medicory.repository.PharmacyRepository;
import com.sameh.medicory.repository.UserRepository;
import com.sameh.medicory.service.admin.users.AdminPharmacyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
//TODO logs
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminPharmacyServiceImpl implements AdminPharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final UserRepository userRepository;
    private final PharmacyMpper pharmacyMpper;
    private final UserMapper userMapper;


    @Override
    public List<PharmacyDTO> showAllPhrmacies() {
        List<Pharmacy> allPharmacies =pharmacyRepository.findAll();
        if(!allPharmacies.isEmpty()){
            return pharmacyMpper.toDTOs(allPharmacies);
        }
        throw new RecordNotFoundException("No pharmacies");

    }

    @Override
    public PharmacyDTO findPharmacyById(Long pharmacyId) {
        if (pharmacyId>0){
            Optional<Pharmacy> optionalPharmacy =pharmacyRepository.findById(pharmacyId);
            if(optionalPharmacy.isPresent()){
                Pharmacy pharmacy=optionalPharmacy.get();
                return pharmacyMpper.toDTO(pharmacy);
            }
            throw new RecordNotFoundException("No pharmacy with this id "+pharmacyId);
        }
        throw new IllegalArgumentException("Invalid Id : "+pharmacyId);
    }

    @Override
    public PharmacyDTO findPharmcyByEmail(String userEmail) {
       Pharmacy pharmacy = pharmacyRepository.findByUserEmail(userEmail);
       if(pharmacy !=null){
           return pharmacyMpper.toDTO(pharmacy);
       }
       throw new RecordNotFoundException("No user *Pharmacy* with this email "+userEmail);
    }

    @Override
    public List<PharmacyDTO> findPharmacyByName(String pharmacyName) {
     List<Pharmacy> pharmacies =pharmacyRepository.findByName(pharmacyName);
       if(!pharmacies.isEmpty()){
           return pharmacyMpper.toDTOs(pharmacies);
       }throw new RecordNotFoundException("No pharmacies with name "+pharmacyName);
    }

    @Override
    public String addPharmacy(PharmacyDTO newPharmacyDTO) {
        Pharmacy newPharmacy =pharmacyMpper.toEntity(newPharmacyDTO);
        User newUser =newPharmacy.getUser();
        User existingUser =userRepository.findByEmail(newUser.getEmail());
        if(existingUser ==null){

            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());
            userRepository.save(newUser);
            pharmacyRepository.save(newPharmacy);
            return "Pharmacy inserted sucessfully";
        }
        throw new ConflictException("User email "+newUser.getEmail()+" already exist");
    }

    @Override
    public String UpdatePharmacy(PharmacyDTO updatedPharmacy, Long pharmacyId) {
        if(pharmacyId>0){
         Optional<Pharmacy> optionalPharmacy=pharmacyRepository.findById(pharmacyId);
         if(optionalPharmacy.isPresent()){
             Pharmacy oldPharmacy =optionalPharmacy.get();
             oldPharmacy.setName(updatedPharmacy.getName());
             oldPharmacy.setAddress(updatedPharmacy.getAddress());
             oldPharmacy.setOwnerName(updatedPharmacy.getOwnerName());
             oldPharmacy.setGoogleMapsLink(updatedPharmacy.getGoogleMapsLink());

             User updatedUser =userMapper.toEntity(updatedPharmacy.getUser());
             if(updatedUser!=null){
                 User oldUser =oldPharmacy.getUser();
                 oldUser.setEmail(updatedUser.getEmail());
                 oldUser.setPassword(updatedUser.getPassword());
                 oldUser.setEnabled(updatedUser.isEnabled());
                 oldUser.setRole(updatedUser.getRole());
                 oldUser.setUpdatedAt(LocalDateTime.now());
                 userRepository.save(oldUser);

             }
             pharmacyRepository.save(oldPharmacy);
             return "Pharmacy updated sucessfully";
         }
         throw new RecordNotFoundException("No pharmacy with id "+pharmacyId);
        }throw  new IllegalArgumentException("Invalid id "+ pharmacyId);
    }

    @Override
    public String deletePharmacy(Long pharmacyId) {
     if(pharmacyId>0){
         Optional<Pharmacy> optionalPharmacy=pharmacyRepository.findById(pharmacyId);
         if(optionalPharmacy.isPresent()){
             Pharmacy pharmacy= optionalPharmacy.get();
             pharmacyRepository.deleteById(pharmacyId);
             userRepository.deleteById(pharmacy.getUser().getId());
             return "deleted sucessfully";

         }throw new RecordNotFoundException("No pharmacy with id "+pharmacyId);
     }
     throw new IllegalArgumentException("Invalid id "+pharmacyId);
    }
}
