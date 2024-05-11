package com.graduationProject.medicory.repository.usersRepositories;

import com.graduationProject.medicory.entity.usersEntities.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ClinicRepository extends JpaRepository<Clinic,Long> {
List<Clinic> findByName(String clinicName);
Optional<Clinic> findByUserEmail(String email);
Optional<Clinic> findByUserCode(String userCode);

}
