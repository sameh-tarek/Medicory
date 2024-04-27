package com.sameh.medicory.repository;

import com.sameh.medicory.entity.usersEntities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository  extends JpaRepository<Doctor,Long> {

Optional<Doctor >findDoctorByUserEmail(String userEmail);
Optional<Doctor> findDoctorByUserCode(String userCode);
List<Doctor> findDoctorByFirstNameAndMiddleNameAndLastName(String fName,String mName,String lName );
List<Doctor> findDoctorByFirstNameAndMiddleName(String fName,String mName);
List<Doctor> findDoctorByFirstNameAndLastName(String fName,String lName);
List<Doctor> findDoctorByFirstName(String fName);
List<Doctor> findDoctorByMiddleName(String name);
List<Doctor> findDoctorByLastName(String  name);
List<Doctor> findDoctorByMiddleNameAndLastName(String mName,String lName);


}
