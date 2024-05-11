package com.graduationProject.medicory.repository.usersRepositories;

import com.graduationProject.medicory.entity.usersEntities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends
        JpaRepository<User, Long> {

   Optional< User> findByEmail(String userEmail);

    Optional<User> findByCode(String code);
}
