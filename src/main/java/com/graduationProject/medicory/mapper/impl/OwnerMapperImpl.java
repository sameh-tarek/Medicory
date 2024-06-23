package com.graduationProject.medicory.mapper.impl;

import com.graduationProject.medicory.entity.phoneEntities.RelativePhoneNumber;
import com.graduationProject.medicory.entity.usersEntities.Owner;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.mapper.usersMappers.OwnerMapper;
import com.graduationProject.medicory.mapper.phonesMappers.RelativePhoneMapper;
import com.graduationProject.medicory.mapper.usersMappers.UserMapper;
import com.graduationProject.medicory.model.owner.OwnerDTO;
import com.graduationProject.medicory.model.phones.RelativePhoneNumberDTO;
import com.graduationProject.medicory.model.users.owner.OwnerRequestDTO;
import com.graduationProject.medicory.model.users.owner.OwnerResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OwnerMapperImpl implements OwnerMapper {

    private final UserMapper userMapper;
    private final RelativePhoneMapper relativePhoneMapper;

    @Override
    public OwnerDTO toDTO(Owner owner) {
        return new OwnerDTO(
                owner.getFirstName() +
                        (owner.getMiddleName() != null ? " " + owner.getMiddleName() : "") +
                        " " + owner.getLastName(),
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

    @Override
    public OwnerRequestDTO toRequestDTO(Owner owner) {
        return new OwnerRequestDTO(
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
                getRelativePhoneNumbers(owner),
                userMapper.toDto(
                        owner.getUser()
                )

        );
    }

    @Override
    public OwnerResponseDTO toResponseDTO(Owner owner) {
        return new OwnerResponseDTO(
                owner.getId(),
                owner.getFirstName() + " "
                        + owner.getMiddleName() + " "
                        + owner.getLastName(),
                owner.getUser().isEnabled()
        );
    }

    @Override
    public Owner toEntity(OwnerRequestDTO owner) {
        Owner newOwner = Owner.builder()
                .id(owner.getId())
                .firstName(owner.getFirstName())
                .middleName(owner.getMiddleName())
                .lastName(owner.getLastName())
                .gender(owner.getGender())
                .dateOfBirth(owner.getDateOfBirth())
                .address(owner.getAddress())
                .bloodType(owner.getBloodType())
                .nationalId(owner.getNationalId())
                .maritalStatus(owner.getMaritalStatus())
                .job(owner.getJob())
                .user(userMapper.toEntity(owner.getUser()))
                .build();

        List<RelativePhoneNumber> relativePhoneNumbers = createRelativePhoneNumbers(newOwner, owner.getRelativePhoneNumbers());
        newOwner.setRelativePhoneNumbers(relativePhoneNumbers);

        return newOwner;
    }

    private int calculateAge(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        if (date != null) {
            return Period.between(date, currentDate).getYears();
        } else
            throw new RuntimeException("Owner Date of Birth is null");
    }

    private List<String> getOwnerPhoneNumber(User user) {
        List<String> phoneNumbers = user.getUserPhoneNumbers()
                .stream()
                .map(phoneNumber -> phoneNumber.getPhone())
                .collect(Collectors.toList());
        return phoneNumbers;
    }

    private List<String> getRelativePhoneNumber(Owner user) {
        List<String> phoneNumbers = user.getRelativePhoneNumbers()
                .stream()
                .map(phoneNumber -> phoneNumber.getPhone())
                .collect(Collectors.toList());
        return phoneNumbers;
    }

    private List<RelativePhoneNumberDTO> getRelativePhoneNumbers(Owner owner) {
        List<RelativePhoneNumberDTO> relativePhoneNumbersDto = new ArrayList<>();
        for (RelativePhoneNumber relativePhoneNumber : owner.getRelativePhoneNumbers()) {
            relativePhoneNumbersDto.add(new RelativePhoneNumberDTO(
                    relativePhoneNumber.getId(),
                    relativePhoneNumber.getPhone(),
                    relativePhoneNumber.getRelation()

            ));
        }
        return relativePhoneNumbersDto;
    }

    public List<RelativePhoneNumber> createRelativePhoneNumbers(Owner owner, List<RelativePhoneNumberDTO> phoneNumbers) {
        return phoneNumbers.stream()
                .map(dto -> {
                    RelativePhoneNumber relativePhoneNumber = new RelativePhoneNumber();
                    relativePhoneNumber.setOwner(owner);
                    relativePhoneNumber.setPhone(dto.getPhone());
                    relativePhoneNumber.setRelation(dto.getRelation().toUpperCase());
                    return relativePhoneNumber;
                })
                .collect(Collectors.toList());

    }

}