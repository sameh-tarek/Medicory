package com.graduationProject.medicory.entity.testsEntities;

import com.graduationProject.medicory.entity.medicationEntities.Prescription;
import com.graduationProject.medicory.entity.usersEntities.Owner;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lab_tests")
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String imageResult;

    private String resultNotes;

    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
