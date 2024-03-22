package com.sameh.medicory.model.immunization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImmunizationResponseDTO {
    private Long id;
    private String name;
    private String information;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
