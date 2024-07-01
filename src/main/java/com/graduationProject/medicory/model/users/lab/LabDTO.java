package com.graduationProject.medicory.model.users.lab;

import com.graduationProject.medicory.entity.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabDTO {

    private long id;

    @NotNull(message = "Lab name cannot be null")
    @NotBlank(message = "Lab name is required")
    @Size(min = 2, max = 100, message = "Lab name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Google Maps link cannot be null")
    @NotBlank(message = "Google Maps link is required")
    @Pattern(regexp = "^(http|https)://[\\w\\.-]+(\\.[a-z]{2,3})?(/.*)?$", message = "Invalid Google Maps link")
    private String googleMapsLink;

    @NotNull(message = "Address cannot be null")
    @NotBlank(message = "Address is required")
    @Size(min = 10, max = 255, message = "Address must be between 10 and 255 characters")
    private String address;

    @NotNull(message = "Owner name cannot be null")
    @NotBlank(message = "Owner name is required")
    @Size(min = 2, max = 100, message = "Owner name must be between 2 and 100 characters")
    private String ownerName;

    private String code;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email format")
    private String email;


    @Size(min = 12, message = "Password must be at least 12 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*.~`'\"(){}\\[\\]+\\-_:<>?/;]).*$",
            message = "Password must have at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;

    @NotNull(message = "Role is required")
    private Role role;

    private boolean isEnabled;

    @NotNull(message = "Created at timestamp is required")
    private LocalDateTime createdAt;

    @NotNull(message = "Updated at timestamp is required")
    private LocalDateTime updatedAt;

    @NotNull(message = "Phone numbers list cannot be null")
    @Size(min = 1, message = "At least one phone number must be provided")
    private List<@Size(min=8,max = 11,message = "phone number can't be less than 8 or exceeded 11") @Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Invalid phone number format") String> userPhoneNumbers;
}
