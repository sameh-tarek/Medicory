package com.sameh.medicory.repository;

import com.sameh.medicory.entity.labTestsEntities.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabTestRepository extends JpaRepository<LabTest, Long> {
}
