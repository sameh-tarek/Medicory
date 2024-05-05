package com.graduationProject.medicory.model.users.admin;

import com.graduationProject.medicory.entity.enums.Gender;
import com.graduationProject.medicory.entity.enums.MaritalStatus;
import com.graduationProject.medicory.model.users.UserDTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AdminRequestDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private MaritalStatus maritalStatus;
    private Gender gender;

    private UserDTO user;

}
