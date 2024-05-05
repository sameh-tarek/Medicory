package com.graduationProject.medicory.mapper;

import com.graduationProject.medicory.entity.phoneEntities.UserPhoneNumber;
import com.graduationProject.medicory.model.phones.UserPhoneNumberDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserPhoneMapper {
    List<UserPhoneNumber> toEntity(List<UserPhoneNumberDTO> userPhoneNumbers);
    List<UserPhoneNumberDTO> toDTO(List<UserPhoneNumber> userPhoneNumbers);
}
