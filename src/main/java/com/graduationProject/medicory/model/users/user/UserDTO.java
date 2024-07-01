package com.graduationProject.medicory.model.users.user;

import com.graduationProject.medicory.entity.enums.Role;
import com.graduationProject.medicory.model.phones.UserPhoneNumberDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String code;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;


    @Size(min = 12, message = "Password must be at least 12 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*.~`'\"(){}\\[\\]+\\-_:<>?/;]).*$",
            message = "Password must contain at least one digit, one lowercase, one uppercase letter, one special character, and no whitespace")
    private String password;

    @NotNull(message = "Role is required")
    private Role role;

    private boolean isEnabled;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @NotEmpty(message = "User phone numbers list cannot be empty")
    private List<@Valid UserPhoneNumberDTO> userPhoneNumbers;
}
