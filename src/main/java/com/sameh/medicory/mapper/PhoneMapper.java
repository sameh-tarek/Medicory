package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.phoneEntities.RelativePhoneNumber;
import com.sameh.medicory.entity.usersEntities.Owner;
import com.sameh.medicory.model.users.RelativePhoneNumberDTO;
import com.sameh.medicory.model.users.owner.OwnerRequestDTO;
import org.mapstruct.Mapper;

import java.util.List;

public interface PhoneMapper {

   List<RelativePhoneNumber> toEntity(List<RelativePhoneNumberDTO> relativePhoneNumberDTO);
}

