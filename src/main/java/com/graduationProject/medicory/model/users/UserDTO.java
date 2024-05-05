package com.graduationProject.medicory.model.users;

import com.graduationProject.medicory.entity.enums.Role;
import com.graduationProject.medicory.model.phones.UserPhoneNumberDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String code;
    private String email;
    private String password;
    private Role role;
    private boolean isEnabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<UserPhoneNumberDTO> userPhoneNumbers;
}
