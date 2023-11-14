package com.sameh.medicory.entity.imagingTestsEntities;

import com.sameh.medicory.entity.usersEntities.Owner;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "imaging_tests")
public class ImagingTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToOne(mappedBy = "imagingTest", cascade = CascadeType.ALL)
    private ImagingTestResult imagingTestResult;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}

