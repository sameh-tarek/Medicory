package com.sameh.medicory.entity.imagingTestsEntities;

import com.sameh.medicory.entity.labTestsEntities.LabTestRequired;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "imaging_tests_required_results")
public class ImagingTestRequiredResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String result;

    @OneToOne
    @JoinColumn(name = "imaging_test_required_id")
    private ImagingTestRequired imagingTestRequired;
}


