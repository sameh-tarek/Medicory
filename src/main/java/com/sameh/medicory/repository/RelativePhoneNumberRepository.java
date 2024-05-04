package com.sameh.medicory.repository;

import com.sameh.medicory.entity.phoneEntities.RelativePhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelativePhoneNumberRepository extends
        JpaRepository<RelativePhoneNumber,Long> {
}
