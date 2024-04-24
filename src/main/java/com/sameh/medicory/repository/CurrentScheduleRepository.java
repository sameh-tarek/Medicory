package com.sameh.medicory.repository;

import com.sameh.medicory.entity.medicationEntities.CurrentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentScheduleRepository extends JpaRepository<CurrentSchedule,Long> {

}
