package com.graduationProject.medicory.repository;

import com.graduationProject.medicory.entity.otherEntities.Immunization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImmunizationRepository extends
        JpaRepository<Immunization, Long> {
}
