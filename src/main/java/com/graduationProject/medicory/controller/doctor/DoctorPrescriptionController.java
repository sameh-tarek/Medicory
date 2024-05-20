package com.graduationProject.medicory.controller.doctor;

import com.graduationProject.medicory.model.prescription.PrescriptionRequestDTO;
import com.graduationProject.medicory.model.prescription.PrescriptionResponseDTO;
import com.graduationProject.medicory.service.doctor.DoctorPrescriptionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/doctors/patients")
public class DoctorPrescriptionController {

    private final DoctorPrescriptionService doctorPrescriptionService;

    @Operation(summary = "Doctor Get All Patient prescriptions")
    @GetMapping("{userCode}/prescriptions")
    public List<PrescriptionResponseDTO> getAllPatientPrescriptions(@PathVariable String userCode){
        return doctorPrescriptionService.getAllPrescriptions(userCode);
    }

    @Operation(summary = "Doctor Add New prescription for Patient")
    @PostMapping("{userCode}/prescriptions")
    public boolean addNewPrescription (@PathVariable String userCode,
                                       @RequestBody PrescriptionRequestDTO prescriptionRequestDTO) {
        return doctorPrescriptionService.addNewPrescription(userCode, prescriptionRequestDTO);
    }



    @Operation(summary = "Doctor Find prescription By Id")
    @GetMapping("prescriptions")
    public PrescriptionResponseDTO findPrescriptionById (@RequestParam Long prescriptionId) {
        return doctorPrescriptionService.findPrescriptionById(prescriptionId);
    }
}
