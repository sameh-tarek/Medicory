package com.sameh.medicory.model.users.lab;

import com.sameh.medicory.model.users.UserDTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LabRequestDTO {
    private Long id;
    private String name;
    private String googleMapsLink;
    private String address;
    private String ownerName;
    private UserDTO user;
}
