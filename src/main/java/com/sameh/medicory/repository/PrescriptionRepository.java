package com.sameh.medicory.repository;

import com.sameh.medicory.entity.medicationEntities.Prescription;
import com.sameh.medicory.model.prescription.PrescriptionResponse;
import jakarta.persistence.Persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrescriptionRepository extends
        JpaRepository<Prescription, Long> {
    @Query("FROM Prescription where Owner.id = :id")
    List<Prescription> findAllByOwnerIdSortedByUpdatedAt(Long id);

    @Query("SELECT p FROM Prescription p where p.owner.id = :id AND p.status=true order by p.updatedAt")
    List<Prescription> findAllActiveByOwnerIdSortedByUpdatedAt(Long id);

}
