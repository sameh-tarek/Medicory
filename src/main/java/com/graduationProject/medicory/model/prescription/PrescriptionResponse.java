package com.graduationProject.medicory.model.prescription;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class PrescriptionResponse {
    private Long prescriptionId;
    private String doctorName;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
