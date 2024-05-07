package com.graduationProject.medicory.service.doctor.impl;

import com.graduationProject.medicory.entity.phoneEntities.RelativePhoneNumber;
import com.graduationProject.medicory.entity.phoneEntities.UserPhoneNumber;
import com.graduationProject.medicory.entity.usersEntities.Owner;
import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.model.patient.PatientPersonalInformation;
import com.graduationProject.medicory.service.doctor.DoctorPatientPersonalInfoService;
import com.graduationProject.medicory.utils.OwnerContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorPatientPersonalInfoServiceImpl implements DoctorPatientPersonalInfoService {

    private final OwnerContext ownerContext;

    @Override
    public PatientPersonalInformation getPatientPersonalInformation(String userCode) {
        User patientUser = ownerContext.getCurrentUser(userCode);
        Owner patientOwner = ownerContext.getCurrentOwner(userCode);
        log.info("Doctor Wants to get patient personal information for owner with id {}", patientOwner.getId());
        PatientPersonalInformation patientPersonalInformation = new PatientPersonalInformation(
                patientOwner.getFirstName() + " " + patientOwner.getMiddleName() + " "
                        + patientOwner.getLastName(),
                getCurrentAge(patientOwner.getDateOfBirth()),
                patientOwner.getGender().name(),
                getPatientPhoneNumbers(patientUser),
                getPatientRelativePhoneNumbers(patientOwner),
                patientOwner.getAddress(),
                patientUser.getEmail(),
                patientOwner.getBloodType().name()
        );
        log.info("patient personal information for owner with id: {} is {}",
                patientOwner.getId(), patientPersonalInformation);
        return patientPersonalInformation;
    }

    private Integer getCurrentAge(LocalDate dateOfBirth) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dateOfBirth, currentDate);
        return period.getYears();
    }

    private List<String> getPatientPhoneNumbers(User patientUser){
        List<UserPhoneNumber> phoneNumbers =patientUser.getUserPhoneNumbers();
        List<String> patientPhoneNumbers = phoneNumbers.stream()
                .map(phoneNumber -> phoneNumber.getPhone())
                .collect(Collectors.toList());
        return patientPhoneNumbers;
    }

    private List<String> getPatientRelativePhoneNumbers(Owner patient){
        List<RelativePhoneNumber> relativePhoneNumbers = patient.getRelativePhoneNumbers();
        List<String> patientRelativePhoneNumbers = relativePhoneNumbers.stream()
                .map(phoneNumber -> phoneNumber.getPhone())
                .collect(Collectors.toList());
        return patientRelativePhoneNumbers;
    }

}
