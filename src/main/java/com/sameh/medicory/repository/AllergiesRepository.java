package com.sameh.medicory.repository;

import com.sameh.medicory.entity.otherEntities.Allergies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllergiesRepository extends
        JpaRepository<Allergies, Long> {
    List<Allergies> findAllergiesByOwnerId(Long ownerId);

}
