package com.graduationProject.medicory.model.tests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImagingTestResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String imageResult;
    private String resultNotes;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
