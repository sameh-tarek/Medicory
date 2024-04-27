package com.sameh.medicory.mapper.impl;

import com.sameh.medicory.entity.usersEntities.Owner;
import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.mapper.OwnerMapper;
import com.sameh.medicory.mapper.UserMapper;
import com.sameh.medicory.model.owner.OwnerDTO;
import com.sameh.medicory.model.users.owner.OwnerRequestDTO;
import com.sameh.medicory.model.users.owner.OwnerResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OwnerMapperImpl implements OwnerMapper {

    @Override
    public OwnerDTO toDTO(Owner owner) {
        return new OwnerDTO(
                owner.getFirstName() +
                        (owner.getMiddleName()!= null ? " " + owner.getMiddleName() : "") +
                        " "+ owner.getLastName(),
                calculateAge(owner.getDateOfBirth()),
                owner.getGender().name(),
                getOwnerPhoneNumber(owner.getUser()),
                getRelativePhoneNumber(owner),
                owner.getUser().getEmail(),
                owner.getAddress(),
                owner.getBloodType().name(),
                owner.getNationalId(),
                owner.getMaritalStatus().name(),
                owner.getJob(),
                owner.getUser().getCreatedAt(),
                owner.getUser().getUpdatedAt()
        );
    }

    @Autowired
    UserMapper userMapper;
    @Override
    public OwnerRequestDTO toRequestDTO(Owner owner) {
        return  new OwnerRequestDTO(
                owner.getId(),
                owner.getFirstName(),
                owner.getMiddleName(),
                owner.getLastName(),
                owner.getGender(),
                owner.getDateOfBirth(),
                owner.getAddress(),
                owner.getBloodType(),
                owner.getNationalId(),
                owner.getMaritalStatus(),
                owner.getJob(),
                userMapper.toDto(
                        owner.getUser()
                )

        );
    }

    @Override
    public OwnerResponseDTO toResponseDTO(Owner owner) {
        return  new OwnerResponseDTO(
                 owner.getId()
                ,owner.getFirstName()+" "
                +owner.getMiddleName()+" "
                +owner.getLastName()
        );
    }

    @Override
    public Owner toEntity(OwnerRequestDTO owner) {
        return new Owner(
                owner.getId(),
                owner.getFirstName(),
                owner.getMiddleName(),
                owner.getLastName(),
                owner.getGender(),
                owner.getDateOfBirth(),
                owner.getAddress(),
                owner.getBloodType(),
                owner.getNationalId(),
                owner.getMaritalStatus(),
                owner.getJob(),
                null,
                null,null,null,null,null,null,
                userMapper.toEntity(owner.getUser()),
                null,null
               );
    }

    private int calculateAge(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        if(date!=null){
            return Period.between(date, currentDate).getYears();
        }else
            throw new RuntimeException("Owner Date of Birth is null");
    }
    private List<String> getOwnerPhoneNumber(User user){
        List<String > phoneNumbers = user.getUserPhoneNumbers()
                .stream()
                .map(phoneNumber -> phoneNumber.getPhone())
                .collect(Collectors.toList());
        return phoneNumbers;
    }
    private List<String> getRelativePhoneNumber(Owner user){
        List<String > phoneNumbers = user.getRelativePhoneNumbers()
                .stream()
                .map(phoneNumber -> phoneNumber.getPhone())
                .collect(Collectors.toList());
        return phoneNumbers;
    }


}
