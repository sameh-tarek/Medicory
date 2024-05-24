package com.graduationProject.medicory.repository.MedicationsRepositories;

import com.graduationProject.medicory.entity.medicationEntities.Medication;
import com.graduationProject.medicory.entity.usersEntities.Owner;
import com.graduationProject.medicory.model.medication.MedicationResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication,Long> {
    List<Medication> findByOwner(Owner owner);

    List<Medication> findByPrescriptionId(Long prescriptionId);
}
