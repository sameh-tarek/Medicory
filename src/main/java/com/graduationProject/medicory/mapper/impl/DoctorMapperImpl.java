package com.graduationProject.medicory.mapper.impl;

import com.graduationProject.medicory.entity.usersEntities.Doctor;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.mapper.usersMappers.DoctorMapper;
import com.graduationProject.medicory.mapper.phonesMappers.UserPhoneNumberMapper;
import com.graduationProject.medicory.model.users.doctor.DoctorDTO;
import com.graduationProject.medicory.model.users.doctor.DoctorRequestDTO;
import com.graduationProject.medicory.model.users.doctor.DoctorResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoctorMapperImpl implements DoctorMapper {

    private final UserPhoneNumberMapper phoneNumberMapper;


    @Override
    public Doctor toRequestEntity(DoctorRequestDTO doctor) {

        return Doctor.builder()
                .firstName(doctor.getFirstName())
                .middleName(doctor.getMiddleName())
                .lastName(doctor.getLastName())
                .specialization(doctor.getSpecialization())
                .licenceNumber(doctor.getLicenceNumber())
                .nationalId(doctor.getNationalId())
                .maritalStatus(doctor.getMaritalStatus())
                .gender(doctor.getGender())
                .user(toUserEntity(doctor))
                .build();
    }

    @Override
    public DoctorDTO toDTO(Doctor doctor) {
        User user = doctor.getUser();
        return DoctorDTO.builder()
                .id(doctor.getId())
                .firstName(doctor.getFirstName())
                .middleName(doctor.getMiddleName())
                .lastName(doctor.getLastName())
                .specialization(doctor.getSpecialization())
                .licenceNumber(doctor.getLicenceNumber())
                .nationalId(doctor.getNationalId())
                .maritalStatus(doctor.getMaritalStatus())
                .gender(doctor.getGender())
                .code(user.getCode())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .isEnabled(user.isEnabled())
                .updatedAt(user.getUpdatedAt())
                .createdAt(user.getCreatedAt())
                .userPhoneNumbers(phoneNumberMapper.toRequestDTO(
                        user.getUserPhoneNumbers()
                ))
                .build();

    }

    @Override
    public DoctorResponseDTO toResponseDTO(Doctor doctor) {
        return new DoctorResponseDTO(
                doctor.getId(),
                doctor.getFirstName()
                        + (doctor.getMiddleName() != null ? " " + doctor.getMiddleName() : "")
                        + " " + doctor.getLastName(),
                doctor.getUser().isEnabled()
        );
    }

    private User toUserEntity(DoctorRequestDTO doctor) {
        return User.builder()
                .email(doctor.getEmail())
                .role(doctor.getRole())
                .enabled(doctor.isEnabled())
                .userPhoneNumbers(
                        phoneNumberMapper.toRequestEntity(doctor.getUserPhoneNumbers())
                )
                .build();
    }
}
