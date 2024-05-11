package com.graduationProject.medicory.controller.admin.users;

import com.graduationProject.medicory.model.users.pharmacy.PharmacyDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyRequestDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyResponseDTO;
import com.graduationProject.medicory.service.admin.users.AdminPharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/pharmacies")
@RequiredArgsConstructor
public class AdminPharmacyController {

    private final AdminPharmacyService pharmacyService;


    @GetMapping("/id/{pharmacyId}/pharmacy")
    public ResponseEntity<PharmacyDTO> showAllDataOfPharmacyById(@PathVariable Long pharmacyId) {
        PharmacyDTO pharmacy = pharmacyService.showAllDataOfPharmacyById(pharmacyId);
        return ResponseEntity.ok(pharmacy);
    }

    @GetMapping("/name/{pharmacyName}")
    public ResponseEntity<List<PharmacyResponseDTO>> findPharmacyByName(@PathVariable String pharmacyName) {
        List<PharmacyResponseDTO> pharmacies = pharmacyService.findPharmacyByName(pharmacyName);
        return ResponseEntity.ok(pharmacies);
    }

    @GetMapping("code/{code}")
    public ResponseEntity<PharmacyResponseDTO> findPharmacyByCode(@PathVariable String code){
        PharmacyResponseDTO pharmacy = pharmacyService.findPharmacyByUserCode(code);
        return new ResponseEntity<>(pharmacy,HttpStatus.OK);
    }

    @GetMapping("/email/{userEmail}")
    public ResponseEntity<PharmacyResponseDTO> findPharmacyByEmail(@PathVariable String userEmail) {
        PharmacyResponseDTO pharmacy = pharmacyService.findPharmacyByUserEmail(userEmail);
        return ResponseEntity.ok(pharmacy);
    }

    @PostMapping("/pharmacy")
    public ResponseEntity<String> addPharmacy(@RequestBody PharmacyRequestDTO newPharmacy) {
        String message = pharmacyService.addPharmacy(newPharmacy);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }


    @PutMapping("/id/{pharmacyId}/pharmacy")
    public ResponseEntity<String> updatePharmacy(@RequestBody PharmacyDTO updatedPharmacy, @PathVariable Long pharmacyId) {
        String message = pharmacyService.updatePharmacy(updatedPharmacy, pharmacyId);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/id/{pharmacyId}")
    public ResponseEntity<String> deletePharmacyById(@PathVariable Long pharmacyId) {
        String message = pharmacyService.deletePharmacy(pharmacyId);
        return ResponseEntity.ok(message);
    }
}