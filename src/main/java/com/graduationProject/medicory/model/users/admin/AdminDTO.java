package com.graduationProject.medicory.model.users.admin;

import com.graduationProject.medicory.entity.enums.Gender;
import com.graduationProject.medicory.entity.enums.MaritalStatus;
import com.graduationProject.medicory.entity.enums.Role;
import com.graduationProject.medicory.model.users.UserDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private MaritalStatus maritalStatus;
    private Gender gender;
    private String code;
    private String email;
    private String password;
    private Role role;
    private boolean isEnabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> userPhoneNumbers;

}
