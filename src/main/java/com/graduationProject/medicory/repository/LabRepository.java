package com.graduationProject.medicory.repository;

import com.graduationProject.medicory.entity.usersEntities.Lab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LabRepository extends JpaRepository<Lab,Long> {
Optional<Lab> findByUserEmail(String userEmail);
Optional<Lab> findByUserCode(String userCode);
List<Lab> findByName(String labName);

}
