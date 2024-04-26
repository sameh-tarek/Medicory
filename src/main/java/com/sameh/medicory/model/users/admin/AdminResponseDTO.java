package com.sameh.medicory.model.users.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponseDTO {
    private long id;
    private String adminName;
}
