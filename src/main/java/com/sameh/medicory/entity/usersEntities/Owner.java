package com.sameh.medicory.entity.usersEntities;

import com.sameh.medicory.entity.enums.MaritalStatus;
import com.sameh.medicory.entity.medicationEntities.Medication;
import com.sameh.medicory.entity.medicationEntities.Prescription;
import com.sameh.medicory.entity.testsEntities.ImagingTest;
import com.sameh.medicory.entity.testsEntities.LabTest;
import com.sameh.medicory.entity.medicationEntities.CurrentSchedule;
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
import java.time.LocalDateTime;
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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_type")
    private BloodType bloodType;

    @Column(name = "national_id")
    private long nationalId;

    @Column(name = "marital_status")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    private String job;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChronicDiseases> chronicDiseases;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Allergies> allergies;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Immunization> immunizations;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Surgery> surgeries;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LabTest> labTests;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImagingTest> imagingTests;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medication> medications;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prescription> prescriptions;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "current_schedule_id")
    private CurrentSchedule currentSchedule;


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

