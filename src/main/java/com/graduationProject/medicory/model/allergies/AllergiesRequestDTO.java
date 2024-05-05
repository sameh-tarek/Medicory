package com.graduationProject.medicory.model.allergies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllergiesRequestDTO {
    private String name;
    private String information;
}
