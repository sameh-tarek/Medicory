package com.sameh.medicory.repository;

import com.sameh.medicory.entity.usersEntities.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PharmacyRepository extends JpaRepository<Pharmacy,Long> {
   Optional< Pharmacy> findByUserEmail(String userEmaail);
   List<Pharmacy> findByName(String pharmaName);
   Optional<Pharmacy> findByUserCode(String userCode);

}
