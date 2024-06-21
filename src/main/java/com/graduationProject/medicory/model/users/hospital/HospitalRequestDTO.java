package com.graduationProject.medicory.model.users.hospital;

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
public class HospitalRequestDTO {

    @NotNull(message = "Hospital name cannot be null")
    @NotBlank(message = "Hospital name is required")
    @Size(min = 2, max = 100, message = "Hospital name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Google Maps link cannot be null")
    @NotBlank(message = "Google Maps link is required")
    @Pattern(regexp = "^(http|https)://[\\w\\.-]+(\\.[a-z]{2,3})?(/.*)?$", message = "Invalid Google Maps link")
    private String googleMapsLink;

    @NotNull(message = "Address cannot be null")
    @NotBlank(message = "Address is required")
    @Size(min = 10, max = 255, message = "Address must be between 10 and 255 characters")
    private String address;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email format")
    private String email;

    @NotNull(message = "Role is required")
    private Role role;

    private boolean isEnabled;

    @NotNull(message = "Phone numbers must not be null")
    @NotEmpty(message = "At least one phone number must be provided")
    @Size(min = 1, message = "At least one phone number must be provided")
    private List<@Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Invalid phone number format") String> userPhoneNumbers;
}
