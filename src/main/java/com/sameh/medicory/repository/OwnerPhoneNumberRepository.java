package com.sameh.medicory.repository;

import com.sameh.medicory.entity.phoneEntities.OwnerPhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerPhoneNumberRepository extends
        JpaRepository<OwnerPhoneNumber, Long> {
    List<OwnerPhoneNumber> findByUserId(Long id);
}
