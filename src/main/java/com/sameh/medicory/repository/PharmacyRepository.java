package com.sameh.medicory.repository;

import com.sameh.medicory.entity.usersEntities.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PharmacyRepository extends JpaRepository<Pharmacy,Long> {
    Pharmacy findByUserEmail(String userEmaail);
   List<Pharmacy> findByName(String pharmaName);

}
