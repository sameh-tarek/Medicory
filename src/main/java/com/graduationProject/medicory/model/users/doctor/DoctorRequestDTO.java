package com.graduationProject.medicory.model.users.doctor;

import com.graduationProject.medicory.entity.enums.Gender;
import com.graduationProject.medicory.entity.enums.MaritalStatus;
import com.graduationProject.medicory.entity.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequestDTO {

    @NotNull(message = "First name cannot be null")
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @Size(max = 50, message = "Middle name cannot exceed 50 characters")
    private String middleName;

    @NotNull(message = "Last name cannot be null")
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotNull(message = "Specialization cannot be null")
    @NotBlank(message = "Specialization is required")
    @Size(min = 3, max = 100, message = "Specialization must be between 3 and 100 characters")
    private String specialization;

    @NotNull(message = "Licence number cannot be null")
    @NotBlank(message = "Licence number is required")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "Licence number must contain only uppercase letters, numbers, and dashes")
    private String licenceNumber;

    @NotNull(message = "National ID cannot be null")
    @NotBlank(message = "National ID is required")
    @Pattern(regexp = "^[0-9]{14}$", message = "National ID must be exactly 14 digits")
    private String nationalId;

    @NotNull(message = "Marital status is required")
    private MaritalStatus maritalStatus;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email format")
    private String email;

    @NotNull(message = "Role is required")
    private Role role;

    private boolean isEnabled;

    @NotNull(message = "Phone numbers list cannot be null")
    @Size(min = 1, message = "At least one phone number must be provided")
    private List<@Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Invalid phone number format") String> userPhoneNumbers;
}
