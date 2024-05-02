package com.sameh.medicory.mapper;

import com.sameh.medicory.entity.phoneEntities.RelativePhoneNumber;
import com.sameh.medicory.model.users.RelativePhoneNumberDTO;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PhoneMapper {

   List<RelativePhoneNumber> toEntity(List<RelativePhoneNumberDTO> relativePhoneNumberDTO);
}

