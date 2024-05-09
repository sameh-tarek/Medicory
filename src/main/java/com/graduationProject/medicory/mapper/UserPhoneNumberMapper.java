package com.graduationProject.medicory.mapper;

import com.graduationProject.medicory.entity.phoneEntities.UserPhoneNumber;

import java.util.List;
public interface UserPhoneNumberMapper {
    List<UserPhoneNumber> toRequestEntity(List<String> numbers);
    List<String> toRequestDTO(List<UserPhoneNumber> phoneNumbers);
}
