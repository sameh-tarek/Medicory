package com.graduationProject.medicory.model.users.lab;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LabSearchResponseDTO {
    private String name;
    private String googleMapsLink;
    private String address;
}
