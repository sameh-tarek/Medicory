package com.sameh.medicory.entity.medicationEntities;

import com.sameh.medicory.entity.usersEntities.Clinic;
import com.sameh.medicory.entity.usersEntities.Hospital;
import com.sameh.medicory.entity.usersEntities.Owner;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prescriptions")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<Medication> medications;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

