package com.graduationProject.medicory.mapper.impl;


import com.graduationProject.medicory.entity.usersEntities.Pharmacy;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.mapper.usersMappers.PharmacyMpper;
import com.graduationProject.medicory.mapper.phonesMappers.UserPhoneNumberMapper;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyRequestDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PharmacyMapperImpl implements PharmacyMpper {
    private final UserPhoneNumberMapper phoneNumberMapper;

    @Override
    public PharmacyDTO toDTO(Pharmacy pharmacy) {
        User user = pharmacy.getUser();
        return PharmacyDTO.builder()
                .name(pharmacy.getName())
                .googleMapsLink(pharmacy.getGoogleMapsLink())
                .address(pharmacy.getAddress())
                .ownerName(pharmacy.getOwnerName())
                .code(user.getCode())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .isEnabled(user.isEnabled())
                .updatedAt(user.getUpdatedAt())
                .createdAt(user.getCreatedAt())
                .userPhoneNumbers(phoneNumberMapper.toRequestDTO(
                        user.getUserPhoneNumbers()
                ))
                .build();
    }


    @Override
    public Pharmacy toRequestEntity(PharmacyRequestDTO pharmacy) {
        return Pharmacy.builder()
                .name(pharmacy.getName())
                .googleMapsLink(pharmacy.getGoogleMapsLink())
                .address(pharmacy.getAddress())
                .ownerName(pharmacy.getOwnerName())
                .user(toUserRequestEntity(pharmacy))
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

    private User toUserRequestEntity(PharmacyRequestDTO pharmacy) {
        return User.builder()
                .role(pharmacy.getRole())
                .enabled(pharmacy.isEnabled())
                .email(pharmacy.getEmail())
                .userPhoneNumbers(
                        phoneNumberMapper.toRequestEntity(pharmacy.getUserPhoneNumbers())
                )
                .build();

    }
}
