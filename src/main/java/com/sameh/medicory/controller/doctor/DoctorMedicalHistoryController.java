package com.sameh.medicory.controller.doctor;

import com.sameh.medicory.entity.medicationEntities.Medication;
import com.sameh.medicory.model.immunization.ImmunizationRequestDTO;
import com.sameh.medicory.model.immunization.ImmunizationResponseDTO;
import com.sameh.medicory.model.allergies.AllergiesRequestDTO;
import com.sameh.medicory.model.allergies.AllergiesResponseDTO;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesRequestDTO;
import com.sameh.medicory.model.chronicDisease.ChronicDiseasesResponseDTO;
import com.sameh.medicory.model.prescription.PrescriptionRequestDTO;
import com.sameh.medicory.model.prescription.PrescriptionResponseDTO;
import com.sameh.medicory.model.surgery.SurgeryRequestDTO;
import com.sameh.medicory.model.surgery.SurgeryResponseDTO;
import com.sameh.medicory.service.doctor.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor/patient")
@RequiredArgsConstructor
public class DoctorMedicalHistoryController {

    private final DoctorService doctorService;

    @GetMapping("/{ownerId}/chronic-diseases")
    public List<ChronicDiseasesResponseDTO> getPatientChronicDiseases(@PathVariable Long ownerId){
        return doctorService.getPatientChronicDiseases(ownerId);
    }

    @PostMapping("/{ownerId}/chronic-diseases")
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewChronicDiseasesForPatient(@RequestBody ChronicDiseasesRequestDTO chronicDiseasesRequestDTO,
                                                  @PathVariable Long ownerId){
        return doctorService.addNewChronicDiseasesForPatient(chronicDiseasesRequestDTO, ownerId);
    }

    @GetMapping("chronic-diseases/{diseasesId}")
    public ChronicDiseasesResponseDTO findChronicDiseasesById(@PathVariable Long diseasesId){
        return doctorService.findChronicDiseasesById(diseasesId);
    }

    @PutMapping("{ownerId}/chronic-diseases/{diseasesId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateChronicDisease(@RequestBody ChronicDiseasesRequestDTO chronicDiseasesRequestDTO,
                                       @PathVariable Long diseasesId, @PathVariable Long ownerId){
        return doctorService.updateChronicDisease(chronicDiseasesRequestDTO, diseasesId, ownerId);
    }

    @DeleteMapping("chronic-diseases/{diseasesId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteChronicDiseases(@PathVariable Long diseasesId){
        return doctorService.deleteChronicDiseases(diseasesId);
    }




    @GetMapping("{ownerId}/allergies")
    public List<AllergiesResponseDTO> getPatientAllergies(@PathVariable Long ownerId){
       return doctorService.getPatientAllergies(ownerId);
    }

    @PostMapping("{ownerId}/allergies")
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewAllergiesForPatient(@RequestBody AllergiesRequestDTO allergiesRequestDTO,
                                            @PathVariable Long ownerId) {
        return doctorService.addNewAllergiesForPatient(allergiesRequestDTO, ownerId);
    }

    @GetMapping("allergies/{allergiesId}")
    public AllergiesResponseDTO findAllergiesById(@PathVariable Long allergiesId){
        return doctorService.findAllergiesById(allergiesId);
    }

    @PutMapping("{ownerId}/allergies/{allergiesId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateAllergies(@RequestBody AllergiesRequestDTO allergiesRequestDTO,
                                  @PathVariable Long allergiesId, @PathVariable Long ownerId){
        return doctorService.updateAllergies(allergiesRequestDTO, allergiesId, ownerId);
    }

    @DeleteMapping("allergies/{allergiesId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteAllergies(@PathVariable Long allergiesId){
        return doctorService.deleteAllergies(allergiesId);
    }





    @GetMapping("{ownerId}/immunizations")
    public List<ImmunizationResponseDTO> getaAllPatientImmunizations(@PathVariable Long ownerId){
       return doctorService.getaAllPatientImmunizations(ownerId);
    }

    @PostMapping("{ownerId}/immunizations")
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewImmunizationForPatient(@RequestBody ImmunizationRequestDTO immunizationRequestDTO,
                                               @PathVariable Long ownerId) {
        return doctorService.addNewImmunizationForPatient(immunizationRequestDTO, ownerId);
    }

    @GetMapping("immunizations/{immunizationId}")
    public ImmunizationResponseDTO findImmunizationById(@PathVariable Long immunizationId){
        return doctorService.findImmunizationById(immunizationId);
    }

    @PutMapping("{ownerId}/immunizations/{immunizationId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateImmunization(@RequestBody ImmunizationRequestDTO immunizationRequestDTO,
                                     @PathVariable Long immunizationId, @PathVariable Long ownerId){
        return doctorService.updateImmunization(immunizationRequestDTO, immunizationId, ownerId);
    }

    @DeleteMapping("immunizations/{immunizationId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteImmunization(@PathVariable Long immunizationId){
        return doctorService.deleteImmunization(immunizationId);
    }




    @GetMapping("{ownerId}/surgeries")
    public List<SurgeryResponseDTO> getPatientSurgicalHistory(@PathVariable Long ownerId){
        return doctorService.getPatientSurgicalHistory(ownerId);
    }

    @PostMapping("{ownerId}/surgeries")
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewSurgeryForPatient(@RequestBody SurgeryRequestDTO surgeryRequestDTO,
                                          @PathVariable Long ownerId) {
        return doctorService.addNewSurgeryForPatient(surgeryRequestDTO, ownerId);
    }

    @GetMapping("surgeries/{surgeryId}")
    public SurgeryResponseDTO findSurgeryById(@PathVariable Long surgeryId){
        return doctorService.findSurgeryById(surgeryId);
    }

    @PutMapping("{ownerId}/surgeries/{surgeryId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateSurgery(@RequestBody SurgeryRequestDTO surgeryRequestDTO,
                                @PathVariable Long surgeryId, @PathVariable Long ownerId){
        return doctorService.updateSurgery(surgeryRequestDTO, surgeryId, ownerId);
    }

    @DeleteMapping("surgeries/{surgeryId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteSurgery(@PathVariable Long surgeryId){
        return doctorService.deleteSurgery(surgeryId);
    }





    @GetMapping("{ownerId}/prescriptions")
    public List<PrescriptionResponseDTO> getAllPatientPrescriptions(@PathVariable Long ownerId){
        return doctorService.getAllPrescriptions(ownerId);
    }

    @PostMapping("{ownerId}/prescriptions")
    public boolean addNewPrescription (@PathVariable Long ownerId,
                                       @RequestBody PrescriptionRequestDTO prescriptionRequestDTO) {
        return doctorService.addNewPrescription(ownerId, prescriptionRequestDTO);
    }

    @GetMapping("prescriptions")
    public PrescriptionResponseDTO findPrescriptionById (@RequestParam Long prescriptionId) {
        return doctorService.findPrescriptionById(prescriptionId);
    }
}
