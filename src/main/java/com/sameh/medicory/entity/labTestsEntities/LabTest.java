package com.sameh.medicory.entity.labTestsEntities;

import com.sameh.medicory.entity.usersEntities.Owner;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lab_tests")
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String imageResult;

    private String resultNotes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
