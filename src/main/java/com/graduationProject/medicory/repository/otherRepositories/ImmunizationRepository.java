package com.graduationProject.medicory.repository.otherRepositories;

import com.graduationProject.medicory.entity.otherEntities.Immunization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImmunizationRepository extends
        JpaRepository<Immunization, Long> {
}
