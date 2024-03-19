package com.sameh.medicory.model.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicDTO {
    private Long id;
    private String name;
    private String googleMapsLink;
    private String address;
    private String ownerName;
    private String specialization;
    private UserDTO user;


}
