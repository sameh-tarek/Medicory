package com.sameh.medicory.repository;

import com.sameh.medicory.entity.usersEntities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends
        JpaRepository<User, Long> {

    User findByEmail(String userEmail);

    Optional<User> findByCode(String code);
}
