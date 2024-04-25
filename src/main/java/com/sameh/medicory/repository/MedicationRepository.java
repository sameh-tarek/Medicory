package com.sameh.medicory.repository;

import com.sameh.medicory.entity.medicationEntities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication,Long> {
}
