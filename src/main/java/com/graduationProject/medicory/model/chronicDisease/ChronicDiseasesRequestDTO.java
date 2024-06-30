package com.graduationProject.medicory.model.chronicDisease;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChronicDiseasesRequestDTO {

    @NotBlank(message = "Chronic Diseases name is required")
    @Size(max = 50, message = "ChronicDiseases name must be at most 50 characters long")
    private String name;

    @NotBlank(message = "Chronic Diseases information is required")
    @Size(max = 200, message = "ChronicDiseases information must be at most 200 characters long")
    private String information;
}
