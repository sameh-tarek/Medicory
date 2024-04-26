package com.sameh.medicory.model.users.pharmacy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyResponseDTO {
    private long  pharmacyId;
    private String pharmacyName;

}
