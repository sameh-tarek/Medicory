package com.sameh.medicory.model.users.doctor;

import com.sameh.medicory.entity.enums.Gender;
import com.sameh.medicory.entity.enums.MaritalStatus;
import com.sameh.medicory.model.users.UserDTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequestDTO {
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
