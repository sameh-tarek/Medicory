package com.sameh.medicory.entity.imagingTestsEntities;

import com.sameh.medicory.entity.labTestsEntities.LabTest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "imaging_tests_results")
public class ImagingTestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String result;

    @OneToOne
    @JoinColumn(name = "imaging_test_id")
    private ImagingTest imagingTest;
}


