package com.graduationProject.medicory.model.users.owner;

import com.graduationProject.medicory.entity.enums.BloodType;
import com.graduationProject.medicory.entity.enums.Gender;
import com.graduationProject.medicory.entity.enums.MaritalStatus;
import com.graduationProject.medicory.model.phones.RelativePhoneNumberDTO;
import com.graduationProject.medicory.model.users.UserDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "First name cannot be null")
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Middle name is required")
    @Size(min = 2, max = 50, message = "Middle name must be between 2 and 50 characters")
    private String middleName;

    @NotNull(message = "Last name cannot be null")
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "Address cannot be null")
    @NotBlank(message = "Address is required")
    @Size(min = 10, max = 255, message = "Address must be between 10 and 255 characters")
    private String address;

    @NotNull(message = "Blood type is required")
    private BloodType bloodType;

    @NotNull(message = "National ID cannot be null")
    @Min(value = 10000000000000L, message = "National ID must be at least 14 digits")
    @Max(value = 99999999999999L, message = "National ID cannot exceed 14 digits")
    private Long nationalId;

    @NotNull(message = "Marital status is required")
    private MaritalStatus maritalStatus;

    @NotBlank(message = "Job is required")
    @Size(min = 2, max = 100, message = "Job must be between 2 and 100 characters")
    private String job;

    @NotNull(message = "Relative phone numbers cannot be null")
    @Size(min = 1, message = "At least one relative phone number must be provided")
    private List<@Valid RelativePhoneNumberDTO> relativePhoneNumbers;

    @Valid
    @NotNull(message = "User details cannot be null")
    private UserDTO user;
}
