package com.graduationProject.medicory.model.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientPersonalInformation {
    private String name;
    private Integer age;
    private String gender;
    private List<String> phoneNumbers;
    private List<String> relativePhoneNumbers;
    private String address;
    private String Email;
    private String bloodType;
 }
