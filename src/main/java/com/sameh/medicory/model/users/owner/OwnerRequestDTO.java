package com.sameh.medicory.model.users.owner;

import com.sameh.medicory.entity.enums.BloodType;
import com.sameh.medicory.entity.enums.Gender;
import com.sameh.medicory.entity.enums.MaritalStatus;
import com.sameh.medicory.model.users.RelativePhoneNumberDTO;
import com.sameh.medicory.model.users.UserDTO;
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
