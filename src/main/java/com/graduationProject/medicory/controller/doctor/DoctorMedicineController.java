package com.graduationProject.medicory.controller.doctor;

import com.graduationProject.medicory.entity.medicationEntities.Medicine;
import com.graduationProject.medicory.service.doctor.DoctorMedicineService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors/medicines")
@AllArgsConstructor
public class DoctorMedicineController {
    private final DoctorMedicineService medicineService;
    @GetMapping
    @Operation(summary = "Search for Medicines By some letters")
    public List<Medicine> searchForMedicine(@RequestParam String name) {
        return medicineService.searchForMedicine(name);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Medicine By Id")
    public Medicine findMedicineById(@PathVariable Long id) {
        return medicineService.findMedicineById(id);
    }
}
