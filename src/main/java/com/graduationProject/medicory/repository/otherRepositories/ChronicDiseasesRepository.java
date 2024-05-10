package com.graduationProject.medicory.repository.otherRepositories;

import com.graduationProject.medicory.entity.otherEntities.ChronicDiseases;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChronicDiseasesRepository extends
        JpaRepository<ChronicDiseases, Long> {
    List<ChronicDiseases> findChronicDiseasesByOwnerId(Long ownerId);
}
