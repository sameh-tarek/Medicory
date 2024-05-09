package com.graduationProject.medicory.model.users.clinic;

import com.graduationProject.medicory.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClinicDTO {
    private  long id ;
    private String name;
    private String googleMapsLink;
    private String address;
    private String ownerName;
    private String specialization;
    private String code;
    private String email;
    private String password;
    private Role role;
    private boolean isEnabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> userPhoneNumbers;


}
