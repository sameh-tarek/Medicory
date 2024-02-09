package com.sameh.medicory.repository;

import com.sameh.medicory.entity.usersEntities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends
        JpaRepository<User, Long> {
}
