package com.sameh.medicory.controller.doctor;

import com.sameh.medicory.model.tests.ImagingTestRequestDTO;
import com.sameh.medicory.model.tests.ImagingTestResponseDTO;
import com.sameh.medicory.model.tests.LabTestRequestDTO;
import com.sameh.medicory.model.tests.LabTestResponseDTO;
import com.sameh.medicory.service.doctor.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors/patients")
@RequiredArgsConstructor
public class DoctorLabController {

    private final DoctorService doctorService;

    @GetMapping("/{userCode}/tests")
    public List<LabTestResponseDTO> getAllLabTestsForPatient(@PathVariable String userCode) {
        return doctorService.findAllLabTestsForPatient(userCode);
    }

    @DeleteMapping("tests/{testId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteLabTestFromHistory(@PathVariable(name = "testId") Long testId){
        return doctorService.deleteLabTestFromHistory(testId);
    }

    @PutMapping("tests/{testId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean updateLabTest(@PathVariable(name = "testId") Long testId, @RequestBody LabTestRequestDTO labTestRequestDTO){
        return doctorService.updateLabTest(testId, labTestRequestDTO);
    }

    @PostMapping("{userCode}/tests")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean addLabTestsForPatientThatRequiredNow (@PathVariable String userCode,
                                                         @RequestBody List<LabTestRequestDTO> requiredTests) {
        return doctorService.addLabTestsForPatientThatRequiredNow(userCode, requiredTests);
    }

    @GetMapping("{userCode}/active-tests")
    public List<LabTestResponseDTO> getActiveLabTests (@PathVariable String userCode) {
        return doctorService.getActiveLabTests(userCode);
    }

    @GetMapping("tests")
    public LabTestResponseDTO findLabTestById (@RequestParam Long testId) {
        return doctorService.findLabTestById(testId);
    }




    @GetMapping("/{userCode}/imaging-tests")
    public List<ImagingTestResponseDTO> getAllImagingTestForPatient(@PathVariable String userCode) {
        return doctorService.getAllImagingTestForPatient(userCode);
    }

    @DeleteMapping("imaging-tests/{testId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean deleteImagingTestFromHistory(@PathVariable(name = "testId") Long testId){
        return doctorService.deleteImagingTestFromHistory(testId);
    }

    @PutMapping("imaging-tests/{testId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean updateImagingTest(@PathVariable(name = "testId") Long testId, @RequestBody ImagingTestRequestDTO imagingTestRequestDTO){
        return doctorService.updateImagingTest(testId, imagingTestRequestDTO);
    }

    @PostMapping("{userCode}/imaging-tests")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean addImagingTestForPatientThatRequiredNow (@PathVariable String userCode,
                                                         @RequestBody List<ImagingTestRequestDTO> requiredTests) {
        return doctorService.addImagingTestForPatientThatRequiredNow(userCode, requiredTests);
    }

    @GetMapping("{userCode}/active-imaging-tests")
    public List<ImagingTestResponseDTO> getActiveImagingTest (@PathVariable String userCode) {
        return doctorService.getActiveImagingTest(userCode);
    }

    @GetMapping("imaging-tests")
    public ImagingTestResponseDTO findImagingTestById (@RequestParam Long testId) {
        return doctorService.findImagingTestById(testId);
    }

}
