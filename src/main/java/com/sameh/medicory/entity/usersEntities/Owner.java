package com.sameh.medicory.entity.usersEntities;

import com.sameh.medicory.entity.Allergies;
import com.sameh.medicory.entity.ChronicDiseases;
import com.sameh.medicory.entity.Immunization;
import com.sameh.medicory.entity.Surgery;
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

    @ManyToMany
    @JoinTable(name = "owner_chronic_diseases",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "chronic_disease_id"))
    private List<ChronicDiseases> chronicDiseases;

    @ManyToMany
    @JoinTable(name = "owner_allergies",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id"))
    private List<Allergies> allergies;

    @ManyToMany
    @JoinTable(name = "owner_immunizations",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "immunization_id"))
    private List<Immunization> immunizations;

    @ManyToMany
    @JoinTable(name = "owner_surgeries",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "surgery_id"))
    private List<Surgery> surgeries;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}

