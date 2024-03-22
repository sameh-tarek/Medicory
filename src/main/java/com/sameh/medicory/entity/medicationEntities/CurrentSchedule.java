package com.sameh.medicory.entity.medicationEntities;

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
@Table(name = "current_schedule")
public class CurrentSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(mappedBy = "currentSchedule", cascade = CascadeType.ALL)
    private List<Medication> medications;

    private LocalDateTime updatedAt;
}
