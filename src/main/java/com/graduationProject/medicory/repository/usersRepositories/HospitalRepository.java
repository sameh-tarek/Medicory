package com.graduationProject.medicory.repository.usersRepositories;

import com.graduationProject.medicory.entity.usersEntities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital ,Long> {
    List<Hospital> findHospitalByName(String hospitalName);

    Optional<Hospital> findHospitalByUserCode(String  userCode);
    Optional<Hospital> findHospitalByUserEmail(String email);
}
