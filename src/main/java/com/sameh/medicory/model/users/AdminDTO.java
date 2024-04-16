package com.sameh.medicory.model.users;

import com.sameh.medicory.entity.enums.Gender;
import com.sameh.medicory.entity.enums.MaritalStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AdminDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private MaritalStatus maritalStatus;
    private Gender gender;

    UserDTO user;

}
