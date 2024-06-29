package com.graduationProject.medicory.model.phones;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPhoneNumberDTO {
    private long id;

    @NotNull(message = "Phone number cannot be null")
    @NotBlank(message = "Phone number is required")
    @Size(min=8,max = 11,message = "phone number can't be less than 8 or exceeded 11")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Invalid phone number format ")
    private String phone;
}
