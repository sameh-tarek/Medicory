package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.usersEntities.Pharmacy;
import com.sameh.medicory.model.users.pharmacy.PharmacyRequestDTO;
import com.sameh.medicory.model.users.pharmacy.PharmacyResponseDTO;

public interface PharmacyMpper {
    PharmacyRequestDTO toDTO(Pharmacy pharmacy);
    Pharmacy toEntity(PharmacyRequestDTO pharmacyRequestDTO);
    PharmacyResponseDTO toResponseDTO(Pharmacy pharmacy);

  }
