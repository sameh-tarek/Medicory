package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.usersEntities.Pharmacy;
import com.sameh.medicory.model.users.PharmacyDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PharmacyMpper {
    PharmacyDTO toDTO(Pharmacy pharmacy);
    Pharmacy toEntity(PharmacyDTO pharmacyDTO);
    List<PharmacyDTO> toDTOs(List<Pharmacy> pharmacyList);
    List<Pharmacy> toEntities(List<PharmacyDTO> pharmacyDTOS);
}
