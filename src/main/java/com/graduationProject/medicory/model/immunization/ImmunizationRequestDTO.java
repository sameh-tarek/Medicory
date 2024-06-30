package com.graduationProject.medicory.model.immunization;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImmunizationRequestDTO {

    @NotBlank(message = "Immunization name is required")
    @Size(max = 50, message = "Immunization name must be at most 50 characters long")
    private String name;

    @NotBlank(message = "Immunization information is required")
    @Size(max = 200, message = "Immunization information must be at most 200 characters long")
    private String information;
}
