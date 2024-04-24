package com.sameh.medicory.model.users;

import com.sameh.medicory.entity.enums.Gender;
import com.sameh.medicory.entity.enums.MaritalStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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
