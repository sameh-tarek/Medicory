package com.graduationProject.medicory.model.surgery;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurgeryRequestDTO {
    @NotBlank(message = "Surgery name is required")
    @Size(max = 50, message = "Surgery name must be at most 50 characters long")
    private String name;

    @NotBlank(message = "Surgery information is required")
    @Size(max = 200, message = "Surgery information must be at most 200 characters long")
    private String description;
}
