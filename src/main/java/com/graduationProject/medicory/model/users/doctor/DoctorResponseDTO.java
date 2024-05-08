package com.graduationProject.medicory.model.users.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponseDTO {
    private long doctorId;
    private String doctorName;
    private boolean isEnabled ;


}
