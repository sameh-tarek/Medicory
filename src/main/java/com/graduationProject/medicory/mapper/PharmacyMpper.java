package com.graduationProject.medicory.mapper;

import com.graduationProject.medicory.entity.usersEntities.Pharmacy;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyRequestDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyResponseDTO;

public interface PharmacyMpper {
    PharmacyDTO toDTO(Pharmacy pharmacy);
    Pharmacy toRequestEntity(PharmacyRequestDTO pharmacy);
    PharmacyResponseDTO toResponseDTO(Pharmacy pharmacy);

  }
