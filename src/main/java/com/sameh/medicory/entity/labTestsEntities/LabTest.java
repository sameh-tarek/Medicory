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

    @OneToOne(mappedBy = "labTest", cascade = CascadeType.ALL)
    private LabTestResult labTestResult;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}


