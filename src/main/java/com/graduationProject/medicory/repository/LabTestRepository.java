package com.graduationProject.medicory.repository;

import com.graduationProject.medicory.entity.testsEntities.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LabTestRepository extends JpaRepository<LabTest, Long> {
    @Query("FROM LabTest where prescription.id = :prescriptionId")
    List<LabTest> findByPrescriptionId(Long prescriptionId);
    @Query("FROM LabTest where prescription.id = :prescriptionId AND status = true ")
    List<LabTest> findActiveTestsByPrescriptionId(Long prescriptionId);
}
