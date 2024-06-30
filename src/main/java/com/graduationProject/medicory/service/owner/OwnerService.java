package com.graduationProject.medicory.service.owner;

import com.graduationProject.medicory.model.allergies.AllergiesResponseDTO;
import com.graduationProject.medicory.model.chronicDisease.ChronicDiseasesResponseDTO;
import com.graduationProject.medicory.model.immunization.ImmunizationResponseDTO;
import com.graduationProject.medicory.model.medication.MedicationDTO;
import com.graduationProject.medicory.model.owner.OwnerResponseDTO;
import com.graduationProject.medicory.model.surgery.SurgeryResponseDTO;
import com.graduationProject.medicory.model.tests.ImagingTestResponseDTO;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicSearchResponseDTO;
import com.graduationProject.medicory.model.users.doctor.DoctorSearchResponseDTO;
import com.graduationProject.medicory.model.users.hospital.HospitalSearchResponseDTO;
import com.graduationProject.medicory.model.users.lab.LabSearchResponseDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacySearchResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OwnerService {
    OwnerResponseDTO getOwnerPersonalInformation(String userCode);

    List<ChronicDiseasesResponseDTO> getOwnerChronicDiseases(String userCode);

    ChronicDiseasesResponseDTO getOwnerChronicDiseaseById(long diseaseId, String userCode);

    List<AllergiesResponseDTO> getOwnerAllergies(String userCode);

    AllergiesResponseDTO getOwnerAllergyById(long diseaseId, String userCode);

    List<ImmunizationResponseDTO> getOwnerImmunizations(String userCode);

    ImmunizationResponseDTO getOwnerImmunizationById(long diseaseId, String userCode);

    List<SurgeryResponseDTO> getOwnerSurgeries(String userCode);

    SurgeryResponseDTO getOwnerSurgeryById(long diseaseId, String userCode);

    List<LabTestResponseDTO> getOwnerLabTests(String userCode);

    LabTestResponseDTO getOwnerLabTestByTestId(long testId, String userCode);

    List<ImagingTestResponseDTO> getOwnerImagingTests(String userCode);

    ImagingTestResponseDTO getOwnerImagingTestByTestId(long testId, String userCode);

    List<MedicationDTO> getCurrentMedicationSchedule(String userCode);

    List<LabSearchResponseDTO> getAllLabs();

    List<LabSearchResponseDTO> getLabsByName(String labName);

    List<PharmacySearchResponseDTO> getAllPharmacies();

    List<PharmacySearchResponseDTO> getPharmaciesByName(String pharmacyName);

    List<ClinicSearchResponseDTO> getAllClinics();

    List<ClinicSearchResponseDTO> getClinicsByName(String clinicName);

    List<ClinicSearchResponseDTO> getClinicsByDoctorName(String doctorName);

    List<HospitalSearchResponseDTO> getAllHospitals();

    List<HospitalSearchResponseDTO> getHospitalsByName(String hospitalName);

    List<DoctorSearchResponseDTO> getAllDoctors();

    List<DoctorSearchResponseDTO> getDoctorsBySpecialization(String specialization);

    List<DoctorSearchResponseDTO> findDoctorByFirstName(String fName);

    List<DoctorSearchResponseDTO> findDoctorByMiddleName(String name);

    List<DoctorSearchResponseDTO> findDoctorByLastName(String  name);

    List<DoctorSearchResponseDTO> findDoctorByFirstNameAndMiddleName(String fName,String mName);

    List<DoctorSearchResponseDTO> findDoctorByFirstNameAndLastName(String fName,String lName);

    List<DoctorSearchResponseDTO> findDoctorByMiddleNameAndLastName(String mName,String lName);

    List<DoctorSearchResponseDTO> findDoctorByFirstNameAndMiddleNameAndLastName(String fName, String mName, String lName );


}
