package com.sameh.medicory.repository;

import com.sameh.medicory.entity.usersEntities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    List<Admin> findAdminByFirstName(String fName);
    Admin findAdminByEmail(String userEmail);

}
