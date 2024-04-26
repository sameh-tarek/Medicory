package com.sameh.medicory.model.users.admin;

import com.sameh.medicory.entity.enums.Gender;
import com.sameh.medicory.entity.enums.MaritalStatus;
import com.sameh.medicory.model.users.UserDTO;
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
