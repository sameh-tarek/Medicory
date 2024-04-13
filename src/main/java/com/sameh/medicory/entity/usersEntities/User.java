package com.sameh.medicory.entity.usersEntities;


import com.sameh.medicory.entity.phoneEntities.OwnerPhoneNumber;
import com.sameh.medicory.entity.enums.Role;
import com.sameh.medicory.entity.phoneEntities.RelativePhoneNumber;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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

    @OneToMany(mappedBy = "user")
    private List<RelativePhoneNumber> relativePhoneNumbers;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", isEnabled=" + isEnabled +
                '}';
    }
}

