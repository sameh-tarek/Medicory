package com.graduationProject.medicory.model.allergies;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllergiesRequestDTO {

    @NotBlank(message = "Allergies name is required")
    @Size(max = 50, message = "Allergies name must be at most 50 characters long")
    private String name;

    @NotBlank(message = "Allergies information is required")
    @Size(max = 200, message = "Allergies information must be at most 200 characters long")
    private String information;
}
