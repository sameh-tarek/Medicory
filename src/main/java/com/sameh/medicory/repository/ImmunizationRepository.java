package com.sameh.medicory.repository;

import com.sameh.medicory.entity.otherEntities.Immunization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImmunizationRepository extends
        JpaRepository<Immunization, Long> {
}
