package com.graduationProject.medicory.model.users.pharmacy;

import com.graduationProject.medicory.model.users.UserDTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PharmacyRequestDTO {
    private Long id;
    private String name;
    private String googleMapsLink;
    private String address;
    private String ownerName;
    private UserDTO user;
}
