package com.graduationProject.medicory.repository;


import com.graduationProject.medicory.entity.medicationEntities.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicineRepository extends
        JpaRepository<Medicine, Long> {
    Optional<Medicine> findByName(String name);
}
