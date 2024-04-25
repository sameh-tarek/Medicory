package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.usersEntities.Owner;
import com.sameh.medicory.model.owner.OwnerDTO;
import com.sameh.medicory.model.users.owner.OwnerRequestDTO;
import com.sameh.medicory.model.users.owner.OwnerResponseDTO;

public interface OwnerMapper {

    OwnerDTO toDTO(Owner owner);
    OwnerRequestDTO toRequestDTO(Owner owner);
    OwnerResponseDTO toResponseDTO(Owner owner);
    Owner toEntity(OwnerRequestDTO owner);
}
