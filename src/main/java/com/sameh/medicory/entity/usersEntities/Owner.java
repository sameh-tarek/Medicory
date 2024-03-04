package com.sameh.medicory.entity.usersEntities;

import com.sameh.medicory.entity.otherEntities.Allergies;
import com.sameh.medicory.entity.otherEntities.ChronicDiseases;
import com.sameh.medicory.entity.otherEntities.Immunization;
import com.sameh.medicory.entity.otherEntities.Surgery;
import com.sameh.medicory.entity.enums.BloodType;
import com.sameh.medicory.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private String address;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChronicDiseases> chronicDiseases;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Allergies> allergies;

    @ManyToMany
    @JoinTable(name = "owner_immunizations",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "immunization_id"))
    private List<Immunization> immunizations;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Surgery> surgeries;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", bloodType=" + bloodType +
                ", chronicDiseases=" + chronicDiseases +
                ", user=" + user +
                '}';
    }
}

