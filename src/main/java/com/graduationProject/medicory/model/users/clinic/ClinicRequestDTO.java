package com.graduationProject.medicory.model.users.clinic;

import com.graduationProject.medicory.entity.enums.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClinicRequestDTO {

    @NotNull(message = "Clinic name cannot be null")
    @NotBlank(message = "Clinic name is required")
    @Size(min = 3, max = 100, message = "Clinic name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Google Maps link is required")
    @Pattern(regexp = "^https?:\\/\\/(www\\.)?google\\.\\w+\\/maps(\\/.*)?$", message = "Invalid Google Maps link format")
    private String googleMapsLink;

    @NotNull(message = "Address cannot be null")
    @NotBlank(message = "Address is required")
    @Size(min = 10, max = 255, message = "Address must be between 10 and 255 characters")
    private String address;

    @NotNull(message = "Owner name cannot be null")
    @NotBlank(message = "Owner name is required")
    @Size(min = 3, max = 50, message = "Owner name must be between 3 and 50 characters")
    private String ownerName;

    @NotNull(message = "Specialization cannot be null")
    @NotBlank(message = "Specialization is required")
    @Size(min = 3, max = 100, message = "Specialization must be between 3 and 100 characters")
    private String specialization;

    @Email(message = "Invalid email format")
    @NotNull(message="Email can not be null")
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email format")
    private String email;

    @NotNull(message = "Role is required")
    private Role role;

    private boolean isEnabled;

    @NotNull(message = "Phone numbers must not be null")
    @NotEmpty(message = "At least one phone number must be provided")    @Size(min = 1, message = "At least one phone number must be provided")
    private List<@Size(min=8,max = 11,message = "phone number can't be less than 8 or exceeded 11") @Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Invalid phone number format") String> userPhoneNumbers;
}
