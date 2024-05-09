package com.graduationProject.medicory.model.users.doctor;

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
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequestDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String specialization;
    private String licenceNumber;
    private String nationalId ;
    private MaritalStatus maritalStatus;
    private Gender gender;
    private String email;
    private Role role;
    private boolean isEnabled;
    private List<String> userPhoneNumbers;

}
