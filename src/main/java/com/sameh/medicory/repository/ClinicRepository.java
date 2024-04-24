package com.sameh.medicory.repository;

import com.sameh.medicory.entity.usersEntities.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClinicRepository extends JpaRepository<Clinic,Long> {
List<Clinic> findByName(String clinicName);
Clinic findByUserEmail(String email);

}
