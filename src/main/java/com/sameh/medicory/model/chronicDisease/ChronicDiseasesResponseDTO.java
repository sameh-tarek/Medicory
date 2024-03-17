package com.sameh.medicory.model.chronicDisease;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChronicDiseasesResponseDTO {
    private Long id;
    private String name;
    private String information;
}
