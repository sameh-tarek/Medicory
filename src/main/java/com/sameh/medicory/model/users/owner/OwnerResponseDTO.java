package com.sameh.medicory.model.users.owner;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OwnerResponseDTO {

    private Long id;

    private String fullName;
    private Boolean isEnabled;
}
