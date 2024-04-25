package com.sameh.medicory.model.tests;

import lombok.Data;

@Data
public class ImagingTestRequestDTO {
    String name;
    String description;
    private String imageResult;
    private String resultNotes;
    private boolean status;
}
