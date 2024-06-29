package com.graduationProject.medicory.model.users.hospital;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HospitalSearchResponseDTO {
    private String name;

    private String googleMapsLink;

    private String address;
}
