package com.graduationProject.medicory.model.users.doctor;

import com.graduationProject.medicory.entity.enums.Gender;
import com.graduationProject.medicory.entity.enums.MaritalStatus;
import com.graduationProject.medicory.model.users.UserDTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String specialization;
    private String licenceNumber;
    private String nationalId ;
    private MaritalStatus maritalStatus;
    private Gender gender;

    private UserDTO user ;
}
