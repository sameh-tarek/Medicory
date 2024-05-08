package com.graduationProject.medicory.controller.doctor;

import com.graduationProject.medicory.model.immunization.ImmunizationRequestDTO;
import com.graduationProject.medicory.model.immunization.ImmunizationResponseDTO;
import com.graduationProject.medicory.model.allergies.AllergiesRequestDTO;
import com.graduationProject.medicory.model.allergies.AllergiesResponseDTO;
import com.graduationProject.medicory.model.chronicDisease.ChronicDiseasesRequestDTO;
import com.graduationProject.medicory.model.chronicDisease.ChronicDiseasesResponseDTO;
import com.graduationProject.medicory.model.surgery.SurgeryRequestDTO;
import com.graduationProject.medicory.model.surgery.SurgeryResponseDTO;
import com.graduationProject.medicory.service.doctor.DoctorMedicalHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors/patients")
@RequiredArgsConstructor
public class DoctorMedicalHistoryController {

    private final DoctorMedicalHistoryService doctorMedicalHistoryService;

    @GetMapping("/{userCode}/chronic-diseases")
    public List<ChronicDiseasesResponseDTO> getPatientChronicDiseases(@PathVariable String userCode){
        return doctorMedicalHistoryService.getPatientChronicDiseases(userCode);
    }

    @PostMapping("/{userCode}/chronic-diseases")
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewChronicDiseasesForPatient(@RequestBody ChronicDiseasesRequestDTO chronicDiseasesRequestDTO,
                                                  @PathVariable String userCode){
        return doctorMedicalHistoryService.addNewChronicDiseasesForPatient(chronicDiseasesRequestDTO, userCode);
    }

    @GetMapping("chronic-diseases/{diseasesId}")
    public ChronicDiseasesResponseDTO findChronicDiseasesById(@PathVariable Long diseasesId){
        return doctorMedicalHistoryService.findChronicDiseasesById(diseasesId);
    }

    @PutMapping("{userCode}/chronic-diseases/{diseasesId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateChronicDisease(@RequestBody ChronicDiseasesRequestDTO chronicDiseasesRequestDTO,
                                       @PathVariable Long diseasesId, @PathVariable String userCode){
        return doctorMedicalHistoryService.updateChronicDisease(chronicDiseasesRequestDTO, diseasesId, userCode);
    }

    @DeleteMapping("chronic-diseases/{diseasesId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteChronicDiseases(@PathVariable Long diseasesId){
        return doctorMedicalHistoryService.deleteChronicDiseases(diseasesId);
    }




    @GetMapping("{userCode}/allergies")
    public List<AllergiesResponseDTO> getPatientAllergies(@PathVariable String userCode){
       return doctorMedicalHistoryService.getPatientAllergies(userCode);
    }

    @PostMapping("{userCode}/allergies")
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewAllergiesForPatient(@RequestBody AllergiesRequestDTO allergiesRequestDTO,
                                            @PathVariable String userCode) {
        return doctorMedicalHistoryService.addNewAllergiesForPatient(allergiesRequestDTO, userCode);
    }

    @GetMapping("allergies/{allergiesId}")
    public AllergiesResponseDTO findAllergiesById(@PathVariable Long allergiesId){
        return doctorMedicalHistoryService.findAllergiesById(allergiesId);
    }

    @PutMapping("{userCode}/allergies/{allergiesId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateAllergies(@RequestBody AllergiesRequestDTO allergiesRequestDTO,
                                  @PathVariable Long allergiesId, @PathVariable String userCode){
        return doctorMedicalHistoryService.updateAllergies(allergiesRequestDTO, allergiesId, userCode);
    }

    @DeleteMapping("allergies/{allergiesId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteAllergies(@PathVariable Long allergiesId){
        return doctorMedicalHistoryService.deleteAllergies(allergiesId);
    }





    @GetMapping("{userCode}/immunizations")
    public List<ImmunizationResponseDTO> getaAllPatientImmunizations(@PathVariable String userCode){
       return doctorMedicalHistoryService.getaAllPatientImmunizations(userCode);
    }

    @PostMapping("{userCode}/immunizations")
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewImmunizationForPatient(@RequestBody ImmunizationRequestDTO immunizationRequestDTO,
                                               @PathVariable String userCode) {
        return doctorMedicalHistoryService.addNewImmunizationForPatient(immunizationRequestDTO, userCode);
    }

    @GetMapping("immunizations/{immunizationId}")
    public ImmunizationResponseDTO findImmunizationById(@PathVariable Long immunizationId){
        return doctorMedicalHistoryService.findImmunizationById(immunizationId);
    }

    @PutMapping("{userCode}/immunizations/{immunizationId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateImmunization(@RequestBody ImmunizationRequestDTO immunizationRequestDTO,
                                     @PathVariable Long immunizationId, @PathVariable String userCode){
        return doctorMedicalHistoryService.updateImmunization(immunizationRequestDTO, immunizationId, userCode);
    }

    @DeleteMapping("immunizations/{immunizationId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteImmunization(@PathVariable Long immunizationId){
        return doctorMedicalHistoryService.deleteImmunization(immunizationId);
    }




    @GetMapping("{userCode}/surgeries")
    public List<SurgeryResponseDTO> getPatientSurgicalHistory(@PathVariable String userCode){
        return doctorMedicalHistoryService.getPatientSurgicalHistory(userCode);
    }

    @PostMapping("{userCode}/surgeries")
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewSurgeryForPatient(@RequestBody SurgeryRequestDTO surgeryRequestDTO,
                                          @PathVariable String userCode) {
        return doctorMedicalHistoryService.addNewSurgeryForPatient(surgeryRequestDTO, userCode);
    }

    @GetMapping("surgeries/{surgeryId}")
    public SurgeryResponseDTO findSurgeryById(@PathVariable Long surgeryId){
        return doctorMedicalHistoryService.findSurgeryById(surgeryId);
    }

    @PutMapping("{userCode}/surgeries/{surgeryId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateSurgery(@RequestBody SurgeryRequestDTO surgeryRequestDTO,
                                @PathVariable Long surgeryId, @PathVariable String userCode){
        return doctorMedicalHistoryService.updateSurgery(surgeryRequestDTO, surgeryId, userCode);
    }

    @DeleteMapping("surgeries/{surgeryId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteSurgery(@PathVariable Long surgeryId){
        return doctorMedicalHistoryService.deleteSurgery(surgeryId);
    }
}
