package com.graduationProject.medicory.controller.doctor;

import com.graduationProject.medicory.model.tests.ImagingTestRequestDTO;
import com.graduationProject.medicory.model.tests.ImagingTestResponseDTO;
import com.graduationProject.medicory.model.tests.LabTestRequestDTO;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import com.graduationProject.medicory.service.doctor.DoctorTestsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/doctors/patients")
@RequiredArgsConstructor
public class DoctorTestsController {

    private final DoctorTestsService doctorTestsService;

    @GetMapping("/{userCode}/tests")
    @Operation(summary = "Get all lab tests for a patient")
    public List<LabTestResponseDTO> getAllLabTestsForPatient(@PathVariable String userCode) {
        return doctorTestsService.findAllLabTestsForPatient(userCode);
    }

    @GetMapping("tests/{prescriptionId}")
    @Operation(summary = "Get all lab tests for a prescription")
    public List<LabTestResponseDTO> getAllPrescriptionLabTestsForPatient(@PathVariable Long prescriptionId) {
        return doctorTestsService.getAllPrescriptionLabTestsForPatient(prescriptionId);
    }

    @DeleteMapping("tests/{testId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Delete lab test from history")
    public String deleteLabTestFromHistory(@PathVariable(name = "testId") Long testId){
        return  doctorTestsService.deleteLabTestFromHistory(testId);
    }

    @PutMapping("tests/{testId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update a lab test")
    public boolean updateLabTest(@PathVariable(name = "testId") Long testId,
                                 @RequestBody LabTestRequestDTO labTestRequestDTO){
        return doctorTestsService.updateLabTest(testId, labTestRequestDTO);
    }

    @PostMapping("{userCode}/tests")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add prescription lab tests for a patient")
    public boolean addPrescriptionLabTestsForPatient (@PathVariable String userCode,
                                                      @RequestBody List<LabTestRequestDTO> requiredTests,
                                                      @RequestParam Long prescriptionId) {
        return doctorTestsService.addPrescriptionLabTestsForPatient(userCode, requiredTests, prescriptionId);
    }

    @GetMapping("{userCode}/active-tests")
    @Operation(summary = "Get active lab tests for a patient")
    public List<LabTestResponseDTO> getActiveLabTests (@PathVariable String userCode) {
        return doctorTestsService.getActiveLabTests(userCode);
    }

    @GetMapping("tests")
    @Operation(summary = "Find a lab test by ID")
    public LabTestResponseDTO findLabTestById (@RequestParam Long testId) {
        return doctorTestsService.findLabTestById(testId);
    }

    @PutMapping("tests/status")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "change lab test status")
    public boolean changeLabTestStatus (@RequestParam Long testId) {
        return doctorTestsService.changeLabTestStatus(testId);
    }

    @GetMapping("/{userCode}/imaging-tests")
    @Operation(summary = "Get all imaging tests for a patient")
    public List<ImagingTestResponseDTO> getAllImagingTestForPatient(@PathVariable String userCode) {
        return doctorTestsService.getAllImagingTestForPatient(userCode);
    }

    @GetMapping("imaging-tests/{prescriptionId}")
    @Operation(summary = "Get all imaging tests for a prescription")
    public List<ImagingTestResponseDTO> getAllPrescriptionImagingTestForPatient(@PathVariable Long prescriptionId) {
        return doctorTestsService.getAllPrescriptionImagingTestForPatient(prescriptionId);
    }

    @DeleteMapping("imaging-tests/{testId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Delete an imaging test from history")
    public boolean deleteImagingTestFromHistory(@PathVariable(name = "testId") Long testId){
        return doctorTestsService.deleteImagingTestFromHistory(testId);
    }

    @PutMapping("imaging-tests/{testId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update an imaging test")
    public boolean updateImagingTest(@PathVariable(name = "testId") Long testId,
                                     @RequestBody ImagingTestRequestDTO imagingTestRequestDTO){
        return doctorTestsService.updateImagingTest(testId, imagingTestRequestDTO);
    }

    @PostMapping("{userCode}/imaging-tests")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add prescription imaging tests for a patient")
    public boolean addPrescriptionImagingTestForPatient (@PathVariable String userCode,
                                                         @RequestBody List<ImagingTestRequestDTO> requiredTests,
                                                         @RequestParam Long prescriptionId) {
        return doctorTestsService.addImagingTestForPatient(userCode, requiredTests, prescriptionId);
    }

    @GetMapping("{userCode}/active-imaging-tests")
    @Operation(summary = "Get active imaging tests for a patient")
    public List<ImagingTestResponseDTO> getActiveImagingTest (@PathVariable String userCode) {
        return doctorTestsService.getActiveImagingTest(userCode);
    }

    @GetMapping("imaging-tests")
    @Operation(summary = "Find an imaging test by ID")
    public ImagingTestResponseDTO findImagingTestById (@RequestParam Long testId) {
        return doctorTestsService.findImagingTestById(testId);
    }

    @PutMapping("imaging-tests/status")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "change imaging test status")
    public boolean changeImagingTestStatus (@RequestParam Long testId) {
        return doctorTestsService.changeImagingTestStatus(testId);
    }

}
