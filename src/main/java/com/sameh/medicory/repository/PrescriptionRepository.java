package com.sameh.medicory.repository;

import com.sameh.medicory.entity.medicationEntities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    @Query("FROM Prescription where owner.user.code = :userCode")
    List<Prescription> findAllByOwnerIdSortedByUpdatedAt(String userCode);

    @Query("SELECT p FROM Prescription p where p.owner.user.code = :userCode AND p.prescriptionStatus=true order by p.updatedAt")
    List<Prescription> findAllActiveByOwnerIdSortedByUpdatedAt(String userCode);

    @Query("FROM Prescription WHERE id=:id AND owner.user.code = :userCode")
    Optional<Prescription> findPrescriptionByUserCodeAndPrescriptionId(String userCode, Long id);

}
