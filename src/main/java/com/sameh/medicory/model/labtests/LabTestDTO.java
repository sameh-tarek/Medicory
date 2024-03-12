package com.sameh.medicory.model.labtests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabTestDTO {
    private String description;
    private String imageResult;
    private String resultNotes;
}
