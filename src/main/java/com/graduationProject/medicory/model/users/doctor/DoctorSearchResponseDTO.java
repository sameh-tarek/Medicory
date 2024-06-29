package com.graduationProject.medicory.model.users.doctor;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorSearchResponseDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String specialization;
}
