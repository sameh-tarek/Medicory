package com.graduationProject.medicory.repository.phoneRepositories;

import com.graduationProject.medicory.entity.phoneEntities.UserPhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerPhoneNumberRepository extends
        JpaRepository<UserPhoneNumber, Long> {
    List<UserPhoneNumber> findByUserId(Long id);
}
