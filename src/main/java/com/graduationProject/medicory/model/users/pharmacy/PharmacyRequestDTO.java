package com.graduationProject.medicory.model.users.pharmacy;

import com.graduationProject.medicory.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyRequestDTO {
    private String name;
    private String googleMapsLink;
    private String address;
    private String ownerName;
    private Role role;
    private String email;
    private boolean isEnabled;
    private List<String> userPhoneNumbers;
}
