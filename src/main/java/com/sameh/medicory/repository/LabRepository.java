package com.sameh.medicory.repository;

import com.sameh.medicory.entity.usersEntities.Lab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabRepository extends JpaRepository<Lab,Long> {
Lab findByUserEmail(String userEmail);
List<Lab> findByName(String labName);
}
