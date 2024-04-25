package com.sameh.medicory.model.users.clinic;

import com.sameh.medicory.model.users.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicRequestDTO {
    private Long id;
    private String name;
    private String googleMapsLink;
    private String address;
    private String ownerName;
    private String specialization;

    private UserDTO user;


}
