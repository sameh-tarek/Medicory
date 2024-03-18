package com.sameh.medicory.entity.medicationEntities;

import com.sameh.medicory.entity.usersEntities.Owner;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medications_currently_required")
//*******************
//TODO to be deleted*
//*******************
public class MedicationCurrentlyRequired {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "medication_required_medicines",
            joinColumns = @JoinColumn(name = "medication_required_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id"))
    private List<Medicine> medicines;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}

