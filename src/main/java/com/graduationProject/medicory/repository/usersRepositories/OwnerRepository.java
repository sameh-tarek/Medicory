package com.graduationProject.medicory.repository.usersRepositories;

import com.graduationProject.medicory.entity.usersEntities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends
        JpaRepository<Owner, Long> {

    Optional<Owner> findAllByUserId(Long userId);

    Optional<Owner> findByUserCode(String userCode);

    List<Owner> findOwnerByFirstNameAndMiddleNameAndLastName(String fName, String mName, String lName);

    List<Owner> findOwnerByFirstNameAndMiddleName(String fName, String mName);

    List<Owner> findOwnerByFirstNameAndLastName(String fName, String lName);

    List<Owner> findOwnerByLastName(String name);
    List<Owner> findOwnerByMiddleName(String name);

    List<Owner> findOwnerByFirstName(String fName);

    List<Owner> findOwnerByMiddleNameAndLastName(String mName, String lName);

    Optional<Owner> findOwnerByUserEmail(String userEmail);

}
