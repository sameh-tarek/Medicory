package com.sameh.medicory.model.users.pharmacy;

import com.sameh.medicory.model.users.UserDTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PharmacyRequestDTO {
    private Long id;
    private String name;
    private String googleMapsLink;
    private String address;
    private String ownerName;
    private UserDTO user;
}
