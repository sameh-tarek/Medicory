package com.graduationProject.medicory.mapper;

import com.graduationProject.medicory.entity.usersEntities.Pharmacy;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyRequestDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyResponseDTO;

public interface PharmacyMpper {
    PharmacyRequestDTO toDTO(Pharmacy pharmacy);
    Pharmacy toEntity(PharmacyRequestDTO pharmacyRequestDTO);
    PharmacyResponseDTO toResponseDTO(Pharmacy pharmacy);

  }
