package com.graduationProject.medicory.repository;

import com.graduationProject.medicory.entity.usersEntities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    List<Admin> findAdminsByFirstNameAndLastName(String fName,String lName);
    List<Admin> findAdminsByFirstName(String fName);
    Optional<Admin> findByUserEmail(String userEmail);
    Optional<Admin> findByUserCode(String  userCode);
    List<Admin> findAdminsByLastName(String name);
    

}
