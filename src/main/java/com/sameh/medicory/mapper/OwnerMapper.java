package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.usersEntities.Owner;
import com.sameh.medicory.model.owner.OwnerDTO;
public interface OwnerMapper {

    OwnerDTO toDTO(Owner owner);

}
