package com.sameh.medicory.model.allergies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllergiesResponseDTO {
    private Long id;
    private String name;
    private String information;
}
