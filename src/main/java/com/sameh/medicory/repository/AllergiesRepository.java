package com.sameh.medicory.repository;

import com.sameh.medicory.entity.otherEntities.Allergies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergiesRepository extends
        JpaRepository<Allergies, Long> {
}
