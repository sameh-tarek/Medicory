package com.sameh.medicory.model.surgery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurgeryResponseDTO {
    private Long id;
    private String description;
}
