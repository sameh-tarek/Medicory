package com.sameh.medicory.controller.admin.users;

import com.sameh.medicory.model.users.PharmacyDTO;
import com.sameh.medicory.service.admin.users.AdminPharmacyService;
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

    @GetMapping
    public ResponseEntity<List<PharmacyDTO>> getAllPharmacies() {
        List<PharmacyDTO> pharmacies = pharmacyService.showAllPhrmacies();
        return ResponseEntity.ok(pharmacies);
    }

    @GetMapping("/pharmacyId/{pharmacyId}")
    public ResponseEntity<PharmacyDTO> getPharmacyById(@PathVariable Long pharmacyId) {
        PharmacyDTO pharmacy = pharmacyService.findPharmacyById(pharmacyId);
        return ResponseEntity.ok(pharmacy);
    }

    @GetMapping("/pharmacyName/{pharmacyName}")
    public ResponseEntity<List<PharmacyDTO>> getPharmaciesByName(@PathVariable String pharmacyName) {
        List<PharmacyDTO> pharmacies = pharmacyService.findPharmacyByName(pharmacyName);
        return ResponseEntity.ok(pharmacies);
    }

    @GetMapping("/userEmail/{userEmail}")
    public ResponseEntity<PharmacyDTO> getPharmacyByEmail(@PathVariable String userEmail) {
        PharmacyDTO pharmacy = pharmacyService.findPharmcyByEmail(userEmail);
        return ResponseEntity.ok(pharmacy);
    }

    @PostMapping("/pharmacy")
    public ResponseEntity<String> addPharmacy(@RequestBody PharmacyDTO newPharmacy) {
        String message = pharmacyService.addPharmacy(newPharmacy);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PutMapping("/pharmacy/{pharmacyId}")
    public ResponseEntity<String> updatePharmacy(@PathVariable Long pharmacyId, @RequestBody PharmacyDTO updatedPharmacy) {
        String message = pharmacyService.UpdatePharmacy(updatedPharmacy, pharmacyId);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/pharmacy/{pharmacyId}")
    public ResponseEntity<String> deletePharmacy(@PathVariable Long pharmacyId) {
        String message = pharmacyService.deletePharmacy(pharmacyId);
        return ResponseEntity.ok(message);
    }
}
