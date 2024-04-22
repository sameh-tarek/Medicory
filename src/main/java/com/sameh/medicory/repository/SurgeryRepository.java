package com.sameh.medicory.repository;

import com.sameh.medicory.entity.otherEntities.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurgeryRepository extends
        JpaRepository<Surgery, Long> {
    List<Surgery> findSurgeriesByOwnerId(Long ownerId);
}
