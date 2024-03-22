package com.sameh.medicory.model.chronicDisease;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChronicDiseasesResponseDTO {
    private Long id;
    private String name;
    private String information;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
