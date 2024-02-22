package com.sameh.medicory.service.impl;

import com.sameh.medicory.entity.otherEntities.ChronicDiseases;
import com.sameh.medicory.entity.phoneEntities.OwnerPhoneNumber;
import com.sameh.medicory.entity.phoneEntities.RelativePhoneNumber;
import com.sameh.medicory.entity.usersEntities.Owner;
import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.mapper.ChronicDiseasesMapper;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesDTO;
import com.sameh.medicory.model.patient.PatientPersonalInformation;
import com.sameh.medicory.service.DoctorService;
import com.sameh.medicory.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final ChronicDiseasesMapper chronicDiseasesMapper;
    private final SecurityUtils securityUtils;

    @Override
    public PatientPersonalInformation getPatientPersonalInformation() {
        User patientUser = securityUtils.getCurrentUser();
        Owner patientOwner = securityUtils.getCurrentOwner();
        PatientPersonalInformation patientPersonalInformation = new PatientPersonalInformation(
                patientOwner.getFirstName() + " " + patientOwner.getMiddleName() + " " + patientOwner.getLastName(),
                getCurrentAge(patientOwner.getDateOfBirth()),
                patientOwner.getGender().name(),
                getPatientPhoneNumbers(patientUser),
                getPatientRelativePhoneNumbers(patientUser),
                patientOwner.getAddress(),
                patientUser.getEmail(),
                patientOwner.getBloodType().name()
        );
        return patientPersonalInformation;
    }
    @Override
    public List<ChronicDiseasesDTO> getPatientChronicDiseases() {
        Owner patientOwner = securityUtils.getCurrentOwner();
        List<ChronicDiseases> chronicDiseases = patientOwner.getChronicDiseases();
        List<ChronicDiseasesDTO> chronicDiseasesDTOS = chronicDiseases
                .stream()
                .map(chronicDiseasesMapper::toDTO)
                .collect(Collectors.toList());
        return chronicDiseasesDTOS;
    }

    private Integer getCurrentAge(LocalDate dateOfBirth) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dateOfBirth, currentDate);
        return period.getYears();
    }

    private List<String> getPatientPhoneNumbers(User patientUser){
        List<OwnerPhoneNumber> phoneNumbers =patientUser.getOwnerPhoneNumbers();
        List<String> patientPhoneNumbers = phoneNumbers.stream()
                .map(phoneNumber -> phoneNumber.getPhone())
                .collect(Collectors.toList());
        return patientPhoneNumbers;
    }

    private List<String> getPatientRelativePhoneNumbers(User patientUser){
        List<RelativePhoneNumber> relativePhoneNumbers = patientUser.getRelativePhoneNumbers();
        List<String> patientRelativePhoneNumbers = relativePhoneNumbers.stream()
                .map(phoneNumber -> phoneNumber.getPhone())
                .collect(Collectors.toList());
        return patientRelativePhoneNumbers;
    }

}
