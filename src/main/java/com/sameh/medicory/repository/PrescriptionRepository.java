package com.sameh.medicory.repository;

import com.sameh.medicory.entity.medicationEntities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends
        JpaRepository<Prescription, Long> {
}
