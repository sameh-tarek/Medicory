package com.graduationProject.medicory.service.doctor;

import com.graduationProject.medicory.model.patient.PatientPersonalInformation;

public interface DoctorPatientPersonalInfoService {
    PatientPersonalInformation getPatientPersonalInformation(String userCode);


}
