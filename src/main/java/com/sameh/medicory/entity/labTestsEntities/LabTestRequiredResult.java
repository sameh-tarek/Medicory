package com.sameh.medicory.entity.labTestsEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lab_tests_required_results")
public class LabTestRequiredResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String result;

    @OneToOne
    @JoinColumn(name = "lab_test_required_id")
    private LabTestRequired labTestRequired;
}


