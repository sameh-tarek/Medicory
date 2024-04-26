package com.sameh.medicory.model.users.lab;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabResponseDTO {
    private long labId;
    private String labName;
}
