package com.sameh.medicory.controller.admin;

import com.sameh.medicory.model.users.PharmacyDTO;
import com.sameh.medicory.service.admin.AdminPharmacyService;
import com.sameh.medicory.service.pharmacy.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/pharmacies")
@RequiredArgsConstructor
public class AdminPharmacyController {
    private final AdminPharmacyService pharmacyService;

    @GetMapping("")
    public List<PharmacyDTO> showAllPharmaicies() {
        return pharmacyService.showAllPhrmacies();
    }

    @GetMapping("/phamacyId/{pharmaId}")
    public PharmacyDTO findPharmacyById(@PathVariable Long pharmaId) {
        return pharmacyService.findPharmacyById(pharmaId);
    }

    @GetMapping("/phamacyName/{pharmaName}")
    public List<PharmacyDTO> findPharmacyByName(@PathVariable String pharmaName) {
        return pharmacyService.findPharmacyByName(pharmaName);
    }

    @GetMapping("/userEmail/{userEmail}")
    public PharmacyDTO findPharmacyByEmail(@PathVariable String userEmail) {
        return pharmacyService.findPharmcyByEmail(userEmail);
    }

    @PostMapping("/pharmacy")
    public String addPharmacy(@RequestBody PharmacyDTO newPharmacy) {
        return pharmacyService.addPharmacy(newPharmacy);
    }
    @PutMapping("/pharmacy/{pharmacyId}")
    public String updatePharmacy (@PathVariable Long pharmacyId,@RequestBody PharmacyDTO updatedPharmacy){
        return pharmacyService.UpdatePharmacy(updatedPharmacy,pharmacyId);
    }
    @DeleteMapping("/pharmacy/{pharmacyId}")
    public String  deletePharmacy(@PathVariable Long pharmacyId){
        return pharmacyService.deletePharmacy(pharmacyId);
    }

}
