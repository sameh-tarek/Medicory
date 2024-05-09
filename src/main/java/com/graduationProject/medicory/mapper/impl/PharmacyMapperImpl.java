package com.graduationProject.medicory.mapper.impl;


import com.graduationProject.medicory.entity.usersEntities.Pharmacy;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.mapper.PharmacyMpper;
import com.graduationProject.medicory.mapper.UserMapper;
import com.graduationProject.medicory.mapper.UserPhoneNumberMapper;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyRequestDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PharmacyMapperImpl implements PharmacyMpper {
   private  final UserMapper map;
   private final UserPhoneNumberMapper phoneNumberMapper;
    @Override
    public PharmacyDTO toDTO(Pharmacy pharmacy) {
        return new PharmacyDTO(
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
    public Pharmacy toEntity(PharmacyDTO pharmacyDTO) {
       return new Pharmacy(
               pharmacyDTO.getId(),
               pharmacyDTO.getName(),
               pharmacyDTO.getGoogleMapsLink(),
               pharmacyDTO.getAddress(),
               pharmacyDTO.getOwnerName(),
               map.toEntity(
               pharmacyDTO.getUser()
               )
       );
    }

    @Override
    public Pharmacy toRequestEntity(PharmacyRequestDTO pharmacy) {
        User user = User.builder()
                .email(pharmacy.getEmail())
                .role(pharmacy.getRole())
                .enabled(pharmacy.isEnabled())
                .userPhoneNumbers(
                        phoneNumberMapper.toRequestEntity(
                                pharmacy.getUserPhoneNumbers()
                        )
                )
                .build();

        return Pharmacy.builder()
                .name(pharmacy.getName())
                .googleMapsLink(pharmacy.getGoogleMapsLink())
                .address(pharmacy.getAddress())
                .ownerName(pharmacy.getOwnerName())
                .build();

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
