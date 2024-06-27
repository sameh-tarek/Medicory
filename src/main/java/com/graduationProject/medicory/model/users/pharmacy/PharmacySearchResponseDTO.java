package com.graduationProject.medicory.model.users.pharmacy;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PharmacySearchResponseDTO {
    private String name;

    private String googleMapsLink;

    private String address;
}
