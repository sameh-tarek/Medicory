package com.sameh.medicory.model.users.clinic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClinicResponseDTO {
    private  Long clinicId ;
    private String clinicName;
    private  boolean isEnabled ;
}
