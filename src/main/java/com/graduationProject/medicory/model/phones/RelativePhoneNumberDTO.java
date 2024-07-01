package com.graduationProject.medicory.model.phones;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelativePhoneNumberDTO {
    private long id;

    @NotNull(message = "Phone number cannot be null")
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Invalid phone number format ")
    @Size(min=8,max = 11,message = "phone number can't be less than 8 or exceeded 11")
    private String phone;

    @NotNull(message = "Relation cannot be null")
    @NotBlank(message = "Relation is required")
    @Size(min = 2, max = 50, message = "Relation must be between 2 and 50 characters")
    private String relation;
}
