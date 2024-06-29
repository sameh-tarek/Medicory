package com.graduationProject.medicory.repository.MedicationsRepositories;

import com.graduationProject.medicory.entity.medicationEntities.Medication;
import com.graduationProject.medicory.entity.usersEntities.Owner;
import com.graduationProject.medicory.model.medication.MedicationResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication,Long> {
    List<Medication> findByOwner(Owner owner);

    List<Medication> findByPrescriptionId(Long prescriptionId);

    @Query("SELECT m FROM Medication m where m.id = :id AND m.owner.user.code = :userCode")
    Optional<Medication> findByIdAndUserCode(Long id, String userCode);
}
