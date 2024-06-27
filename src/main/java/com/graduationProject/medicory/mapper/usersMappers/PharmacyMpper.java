package com.graduationProject.medicory.mapper.usersMappers;

import com.graduationProject.medicory.entity.usersEntities.Pharmacy;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyRequestDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyResponseDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacySearchResponseDTO;

public interface PharmacyMpper {
    PharmacyDTO toDTO(Pharmacy pharmacy);
    Pharmacy toRequestEntity(PharmacyRequestDTO pharmacy);
    PharmacyResponseDTO toResponseDTO(Pharmacy pharmacy);

    PharmacySearchResponseDTO toSearchResponseDTO(Pharmacy pharmacy);
  }
