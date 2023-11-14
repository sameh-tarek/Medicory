package com.sameh.medicory.entity.labTestsEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lab_tests_results")
public class LabTestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String result;

    @OneToOne
    @JoinColumn(name = "lab_test_id")
    private LabTest labTest;
}

