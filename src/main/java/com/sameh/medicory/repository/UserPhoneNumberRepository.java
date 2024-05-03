package com.sameh.medicory.repository;

import com.sameh.medicory.entity.phoneEntities.UserPhoneNumber;
import com.sameh.medicory.entity.usersEntities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPhoneNumberRepository extends JpaRepository<UserPhoneNumber ,Long> {

    Optional<User> findUserByPhone(String phone);
}
