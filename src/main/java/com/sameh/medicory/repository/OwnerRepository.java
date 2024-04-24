package com.sameh.medicory.repository;

import com.sameh.medicory.entity.usersEntities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends
        JpaRepository<Owner, Long> {

    Optional<Owner> findAllByUserId(Long userId);

    Optional<Owner> findByUserCode(String userCode);
}
