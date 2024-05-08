package com.graduationProject.medicory.model.users.owner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerResponseDTO {

    private Long id;

    private String fullName;
    private Boolean isEnabled;
}
