package com.graduationProject.medicory.model.users.admin;

import com.graduationProject.medicory.entity.enums.Gender;
import com.graduationProject.medicory.entity.enums.MaritalStatus;
import com.graduationProject.medicory.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequestDTO {

    private String firstName;
    private String lastName;
    private MaritalStatus maritalStatus;
    private Gender gender;
    private Role role;
    private String email;
    private boolean isEnabled;
    private List<String> userPhoneNumbers;
}
