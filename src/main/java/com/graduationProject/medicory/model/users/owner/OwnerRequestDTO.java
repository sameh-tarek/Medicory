package com.graduationProject.medicory.model.users.owner;

import com.graduationProject.medicory.entity.enums.BloodType;
import com.graduationProject.medicory.entity.enums.Gender;
import com.graduationProject.medicory.entity.enums.MaritalStatus;
import com.graduationProject.medicory.model.phones.RelativePhoneNumberDTO;
import com.graduationProject.medicory.model.users.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OwnerRequestDTO {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String address;
    private BloodType bloodType;
    private long nationalId;
    private MaritalStatus maritalStatus;
    private String job;

    private List<RelativePhoneNumberDTO> relativePhoneNumbers;


    private UserDTO user;
}
