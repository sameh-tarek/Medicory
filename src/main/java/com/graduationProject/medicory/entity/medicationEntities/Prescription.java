package com.graduationProject.medicory.entity.medicationEntities;

import com.graduationProject.medicory.entity.testsEntities.ImagingTest;
import com.graduationProject.medicory.entity.testsEntities.LabTest;
import com.graduationProject.medicory.entity.usersEntities.Doctor;
import com.graduationProject.medicory.entity.usersEntities.Owner;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prescriptions")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medication_status")
    private boolean medicationStatus;

    @Column(name = "prescription_status")
    private boolean prescriptionStatus;

    @Column(name = "is_lab_needed")
    private boolean isLabNeeded;
    @Column(name = "is_pharmacy_needed")
    private boolean isPharmacyNeeded;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<Medication> medications;

    @OneToMany
    @JoinColumn(name = "prescription_id")
    private List<LabTest> tests;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<ImagingTest> imagingTests;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}

