package com.graduationProject.medicory.model.users.doctor;

import com.graduationProject.medicory.entity.enums.Gender;
import com.graduationProject.medicory.entity.enums.MaritalStatus;
import com.graduationProject.medicory.entity.enums.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String specialization;
    private String licenceNumber;
    private String nationalId ;
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
