package com.sameh.medicory.repository;

import com.sameh.medicory.entity.usersEntities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository  extends JpaRepository<Doctor,Long> {

Doctor findDoctorByUserEmail(String userEmail);

List<Doctor> findDoctorByFirstNameAndMiddleNameAndLastName(String fName,String mName,String lName );
List<Doctor> findDoctorByFirstNameAndMiddleName(String fName,String mName);

List<Doctor> findDoctorByFirstNameAndLastName(String fName,String lName);
List<Doctor> findDoctorByFirstName(String fName);

}
