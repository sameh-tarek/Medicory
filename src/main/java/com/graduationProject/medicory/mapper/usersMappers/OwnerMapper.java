package com.graduationProject.medicory.mapper.usersMappers;

import com.graduationProject.medicory.entity.usersEntities.Owner;
import com.graduationProject.medicory.model.owner.OwnerDTO;
import com.graduationProject.medicory.model.users.owner.OwnerRequestDTO;
import com.graduationProject.medicory.model.users.owner.OwnerResponseDTO;

public interface OwnerMapper {

    OwnerDTO toDTO(Owner owner);
    OwnerRequestDTO toRequestDTO(Owner owner);
    OwnerResponseDTO toResponseDTO(Owner owner);
    Owner toEntity(OwnerRequestDTO owner);
}
