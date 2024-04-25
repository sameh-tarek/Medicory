package com.sameh.medicory.model.users;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PharmacyDTO {
    private Long id;
    private String name;
    private String googleMapsLink;
    private String address;
    private String ownerName;
    private UserDTO user;
}
