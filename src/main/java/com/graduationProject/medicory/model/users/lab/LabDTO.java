package com.graduationProject.medicory.model.users.lab;

import com.graduationProject.medicory.entity.enums.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabDTO {
    private  long id;
    private String name;
    private String googleMapsLink;
    private String address;
    private String ownerName;
    private String code;
    private String email;
    private String password;
    private Role role;
    private boolean isEnabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> userPhoneNumbers;
}
