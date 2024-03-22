package com.sameh.medicory.entity.medicationEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medications")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    @ManyToOne
    @JoinColumn(name = "current_schedule_id")
    private CurrentSchedule currentSchedule;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    @Column(nullable = false)
    private String dose;

    @Column(nullable = false)
    private int frequency;

    @Column(name = "side_effects")
    private String sideEffects;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
