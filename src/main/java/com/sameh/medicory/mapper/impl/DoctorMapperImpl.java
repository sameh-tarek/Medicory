package com.sameh.medicory.mapper.impl;

import com.sameh.medicory.entity.usersEntities.Doctor;
import com.sameh.medicory.mapper.DoctorMapper;
import com.sameh.medicory.mapper.UserMapper;
import com.sameh.medicory.model.users.Doctor.DoctorRequestDTO;
import com.sameh.medicory.model.users.Doctor.DoctorResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoctorMapperImpl implements DoctorMapper {

    private  final UserMapper userMapper;
    @Override
    public Doctor toEntity(DoctorRequestDTO doctorRequest) {
        return new Doctor(
                doctorRequest.getId(),
                doctorRequest.getFirstName(),
                doctorRequest.getMiddleName(),
                doctorRequest.getLastName(),
                doctorRequest.getSpecialization(),
                doctorRequest.getLicenceNumber(),
                doctorRequest.getNationalId(),
                doctorRequest.getMaritalStatus(),
                doctorRequest.getGender(),
                userMapper.toEntity(
                        doctorRequest.getUser()
                )
        );
    }

    @Override
    public DoctorRequestDTO toDTO(Doctor doctor) {
        return new DoctorRequestDTO(
                doctor.getId(),
                doctor.getFirstName(),
                doctor.getMiddleName(),
                doctor.getLastName(),
                doctor.getSpecialization(),
                doctor.getLicenceNumber(),
                doctor.getNationalId(),
                doctor.getMaritalStatus(),
                doctor.getGender(),
                userMapper.toDto(
                        doctor.getUser()
                )
        );
    }

    @Override
    public DoctorResponseDTO toResponseDTO(Doctor doctor) {
      return new DoctorResponseDTO(
              doctor.getId(),
              doctor.getFirstName()
                      + (doctor.getMiddleName()!= null ? " " + doctor.getMiddleName() : "")
                      + " " + doctor.getLastName()
      );
    }
}
