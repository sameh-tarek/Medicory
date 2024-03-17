package com.sameh.medicory.model.immunization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImmunizationRequestDTO {
    private String name;
    private String information;
}
