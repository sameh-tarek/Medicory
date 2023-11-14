package com.sameh.medicory.entity.usersEntities;


import com.sameh.medicory.entity.phoneEntities.OwnerPhoneNumber;
import com.sameh.medicory.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @OneToMany(mappedBy = "user")
    private List<OwnerPhoneNumber> ownerPhoneNumbers;
}

