package com.graduationProject.medicory.repository;

import com.graduationProject.medicory.entity.testsEntities.ImagingTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagingTestRepository extends
        JpaRepository<ImagingTest, Long> {
}
