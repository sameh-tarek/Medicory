package com.graduationProject.medicory.repository.MedicationsRepositories;

import com.graduationProject.medicory.entity.medicationEntities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication,Long> {
}
