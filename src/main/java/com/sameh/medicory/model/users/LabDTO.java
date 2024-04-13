package com.sameh.medicory.model.users;

import com.sameh.medicory.entity.usersEntities.User;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LabDTO {
    private Long id;
    private String name;
    private String googleMapsLink;
    private String address;
    private String ownerName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private  UserDTO user;
}
