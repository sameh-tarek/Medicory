package com.graduationProject.medicory.model.tests;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabTestResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String testNotes;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
