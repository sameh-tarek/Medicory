package com.graduationProject.medicory.repository;

import com.graduationProject.medicory.entity.phoneEntities.RelativePhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelativePhoneNumberRepository extends
        JpaRepository<RelativePhoneNumber,Long> {
}
