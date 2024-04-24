package com.sameh.medicory.repository;

import com.sameh.medicory.entity.testsEntities.ImagingTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagingTestRepository extends
        JpaRepository<ImagingTest, Long> {
}
