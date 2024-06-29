package com.graduationProject.medicory.model.users.clinic;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClinicSearchResponseDTO {
    private String name;

    private String googleMapsLink;

    private String address;

    private String doctor;
}
