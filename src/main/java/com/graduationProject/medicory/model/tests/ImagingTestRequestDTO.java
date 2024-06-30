package com.graduationProject.medicory.model.tests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ImagingTestRequestDTO {
    @NotBlank(message = "Imaging Test name is required")
    @Size(max = 50, message = "Imaging Test name must be at most 50 characters long")
    String name;

    @NotBlank(message = "Imaging Test description is required")
    @Size(max = 200, message = "Imaging Test description must be at most 200 characters long")
    String description;

    @NotBlank(message = "Image result is required")
    private String imageResult;

    private String resultNotes;

    @NotBlank(message = "Status is required")
    private boolean status;
}
