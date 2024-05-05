package com.graduationProject.medicory.model.allergies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllergiesResponseDTO {
    private Long id;
    private String name;
    private String information;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
