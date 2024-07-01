package com.graduationProject.medicory.mapper.usersMappers;

import com.graduationProject.medicory.entity.usersEntities.Owner;
import com.graduationProject.medicory.model.owner.OwnerResponseDTO;
import com.graduationProject.medicory.model.users.owner.OwnerRequestDTO;

public interface OwnerMapper {

    OwnerResponseDTO toDTO(Owner owner);
    OwnerRequestDTO toRequestDTO(Owner owner);
    com.graduationProject.medicory.model.users.owner.OwnerResponseDTO toResponseDTO(Owner owner);
    Owner toEntity(OwnerRequestDTO owner);
}
