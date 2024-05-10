package com.graduationProject.medicory.repository.phoneRepositories;

import com.graduationProject.medicory.entity.phoneEntities.UserPhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPhoneNumberRepository extends JpaRepository<UserPhoneNumber ,Long> {

    Optional<UserPhoneNumber> findUserByPhone(String phone);
}
