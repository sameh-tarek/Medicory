package com.sameh.medicory.repository;

import com.sameh.medicory.entity.otherEntities.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurgeryRepository extends
        JpaRepository<Surgery, Long> {
}
