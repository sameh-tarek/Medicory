package com.sameh.medicory.model.users;

import com.sameh.medicory.entity.enums.Role;
import com.sameh.medicory.model.phones.UserPhoneNumberDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
