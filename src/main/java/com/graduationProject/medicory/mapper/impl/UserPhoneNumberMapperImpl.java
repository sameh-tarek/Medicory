package com.graduationProject.medicory.mapper.impl;

import com.graduationProject.medicory.entity.phoneEntities.UserPhoneNumber;
import com.graduationProject.medicory.mapper.UserPhoneNumberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserPhoneNumberMapperImpl implements UserPhoneNumberMapper {
    @Override
    public List<UserPhoneNumber> toRequestEntity(List<String> numbers) {
        List<UserPhoneNumber> userPhoneNumbers =numbers
                .stream()
                .map(phoneNumber -> UserPhoneNumber.builder()
                        .phone(phoneNumber)
                        .build())
                .collect(Collectors.toList());
        return  userPhoneNumbers;
    }

    @Override
    public List<String> toRequestDTO(List<UserPhoneNumber> phoneNumbers) {
        List<String> userPhoneNumbers = phoneNumbers
                .stream()
                .map(UserPhoneNumber::getPhone)
                .collect(Collectors.toList());

        return userPhoneNumbers;
    }

}
