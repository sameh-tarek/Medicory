package com.graduationProject.medicory.repository.testsRepositories;

import com.graduationProject.medicory.entity.testsEntities.ImagingTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImagingTestRepository extends JpaRepository<ImagingTest, Long> {
    @Query("FROM ImagingTest WHERE prescription.id = :prescriptionId")
    List<ImagingTest> findByPrescriptionId(Long prescriptionId);

    @Query("FROM ImagingTest where prescription.id = :prescriptionId AND status = true")
    List<ImagingTest> findActiveTestsByPrescriptionId(Long prescriptionId);
}
