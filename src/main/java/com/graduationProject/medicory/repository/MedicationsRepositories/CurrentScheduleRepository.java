package com.graduationProject.medicory.repository.MedicationsRepositories;

import com.graduationProject.medicory.entity.medicationEntities.CurrentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrentScheduleRepository extends JpaRepository<CurrentSchedule,Long> {


    Optional<CurrentSchedule> findByOwnerId(Long id);

}
