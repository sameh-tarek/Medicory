package com.graduationProject.medicory.model.surgery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurgeryRequestDTO {
    private String name;
    private String description;
}
