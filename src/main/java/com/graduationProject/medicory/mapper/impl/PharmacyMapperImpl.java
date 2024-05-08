package com.graduationProject.medicory.mapper.impl;


import com.graduationProject.medicory.entity.usersEntities.Pharmacy;
import com.graduationProject.medicory.mapper.PharmacyMpper;
import com.graduationProject.medicory.mapper.UserMapper;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyRequestDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PharmacyMapperImpl implements PharmacyMpper {
   private  final UserMapper map;
    @Override
    public PharmacyRequestDTO toDTO(Pharmacy pharmacy) {
        return new PharmacyRequestDTO(
                pharmacy.getId(),
                pharmacy.getName(),
                pharmacy.getGoogleMapsLink(),
                pharmacy.getAddress(),
                pharmacy.getOwnerName(),
                map.toDto(
                pharmacy.getUser()
                )
        );

    }

    @Override
    public Pharmacy toEntity(PharmacyRequestDTO pharmacyRequestDTO) {
       return new Pharmacy(
               pharmacyRequestDTO.getId(),
               pharmacyRequestDTO.getName(),
               pharmacyRequestDTO.getGoogleMapsLink(),
               pharmacyRequestDTO.getAddress(),
               pharmacyRequestDTO.getOwnerName(),
               map.toEntity(
               pharmacyRequestDTO.getUser()
               )
       );
    }

    @Override
    public PharmacyResponseDTO toResponseDTO(Pharmacy pharmacy) {
        return new PharmacyResponseDTO(
                pharmacy.getId(),
                pharmacy.getName(),
                pharmacy.getUser().isEnabled()
        );
    }
}
