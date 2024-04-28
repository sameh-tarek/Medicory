package com.sameh.medicory.mapper.impl;

import com.sameh.medicory.entity.phoneEntities.RelativePhoneNumber;
import com.sameh.medicory.entity.usersEntities.Owner;
import com.sameh.medicory.mapper.PhoneMapper;
import com.sameh.medicory.model.users.RelativePhoneNumberDTO;
import com.sameh.medicory.model.users.owner.OwnerRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RelativePhoneMapperImpl implements PhoneMapper {
    @Override
    public List< RelativePhoneNumber> toEntity(List<RelativePhoneNumberDTO> relativePhoneNumbers) {
         List<RelativePhoneNumber> phoneNumbers = relativePhoneNumbers.stream()
                 .map( relativePhoneNumberDTO -> {
                     RelativePhoneNumber  relativePhoneNumber =new RelativePhoneNumber();
                     relativePhoneNumber.setPhone(relativePhoneNumberDTO.getPhoneNumber());
                     relativePhoneNumber.setRelation(relativePhoneNumberDTO.getRelationship());
                     return relativePhoneNumber;
                         }
                 ).collect(Collectors.toList());
         return phoneNumbers ;
    }
}
