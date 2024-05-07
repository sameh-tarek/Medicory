package com.graduationProject.medicory.controller.doctor;

import com.graduationProject.medicory.model.prescription.PrescriptionRequestDTO;
import com.graduationProject.medicory.model.prescription.PrescriptionResponseDTO;
import com.graduationProject.medicory.service.doctor.DoctorPrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/doctors/patients")
public class DoctorPrescriptionController {

    private final DoctorPrescriptionService doctorPrescriptionService;

    @GetMapping("{userCode}/prescriptions")
    public List<PrescriptionResponseDTO> getAllPatientPrescriptions(@PathVariable String userCode){
        return doctorPrescriptionService.getAllPrescriptions(userCode);
    }

    @PostMapping("{userCode}/prescriptions")
    public boolean addNewPrescription (@PathVariable String userCode,
                                       @RequestBody PrescriptionRequestDTO prescriptionRequestDTO) {
        return doctorPrescriptionService.addNewPrescription(userCode, prescriptionRequestDTO);
    }

    @GetMapping("prescriptions")
    public PrescriptionResponseDTO findPrescriptionById (@RequestParam Long prescriptionId) {
        return doctorPrescriptionService.findPrescriptionById(prescriptionId);
    }
}
