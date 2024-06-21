package com.graduationProject.medicory.model.users.admin;

import com.graduationProject.medicory.entity.enums.Gender;
import com.graduationProject.medicory.entity.enums.MaritalStatus;
import com.graduationProject.medicory.entity.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    private  long id;

    @NotNull(message= "First name can not be null")
    @NotBlank(message = "First name is required")
    @Size(min=3,max=15,message = "First name can not be less than 3 or exceed 15")
    private String firstName;

    @NotNull(message= "Last name can not be null")
    @NotBlank(message = "Last name is required")
    @Size(min=3,max=15,message = "Last name can not be less than 3 or exceed 15")
    private String lastName;

    @NotNull(message = "Marital status is required")
    private MaritalStatus maritalStatus;

    @NotNull(message = "Gender is required")
    private Gender gender;

    private String code;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"
            ,message = "Invalid email"
    )
    private String email;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    @Size(min = 12, message = "Password must be at least 12 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",
            message = "Password must contain at least one digit, one uppercase letter, one lowercase letter, and one special character")
    private String password;

    @NotNull(message = "Role is required")
    private Role role;

    @NotNull(message = "Must indicate if enabled or not")
    private boolean isEnabled;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @NotNull(message = "Phone numbers must not be null")
    @Size(min = 1, message = "At least one phone number must be provided")
    private List<@Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Invalid phone number format" )String> userPhoneNumbers;

}
