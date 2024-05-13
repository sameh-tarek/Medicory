package com.graduationProject.medicory.controller.admin.users;

import com.graduationProject.medicory.model.users.pharmacy.PharmacyDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyRequestDTO;
import com.graduationProject.medicory.model.users.pharmacy.PharmacyResponseDTO;
import com.graduationProject.medicory.service.admin.users.pharmacy.AdminPharmacyService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/pharmacies")
@RequiredArgsConstructor
@OpenAPIDefinition(
        info = @Info(
                description = "Admin handle user 'PHARMACY' "
        )
)
public class AdminPharmacyController {

    private final AdminPharmacyService pharmacyService;


    @GetMapping("/id/{pharmacyId}/pharmacy")
    @Operation(summary = "Get all data of pharmacy found by search with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, pharmacy found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No pharmacy with this id ")
    })
    public ResponseEntity<PharmacyDTO> showAllDataOfPharmacyById(@PathVariable Long pharmacyId) {
        PharmacyDTO pharmacy = pharmacyService.showAllDataOfPharmacyById(pharmacyId);
        return ResponseEntity.ok(pharmacy);
    }

    @GetMapping("/name/{pharmacyName}")
    @Operation(summary = "Search for pharmacy by its name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, pharmacys found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No pharmacys with this name")
    })
    public ResponseEntity<List<PharmacyResponseDTO>> findPharmacyByName(@PathVariable String pharmacyName) {
        List<PharmacyResponseDTO> pharmacies = pharmacyService.findPharmacyByName(pharmacyName);
        return ResponseEntity.ok(pharmacies);
    }

    @GetMapping("code/{code}")
    @Operation(summary = "Get all data of pharmacy found by search with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, pharmacy found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No pharmacy with this id ")
    })
    public ResponseEntity<PharmacyResponseDTO> findPharmacyByCode(@PathVariable String code){
        PharmacyResponseDTO pharmacy = pharmacyService.findPharmacyByUserCode(code);
        return new ResponseEntity<>(pharmacy,HttpStatus.OK);
    }

    @GetMapping("/email/{userEmail}")
    @Operation(summary = "Search for pharmacy by its email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, pharmacy found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No pharmacy with this email")
    })
    public ResponseEntity<PharmacyResponseDTO> findPharmacyByEmail(@PathVariable String userEmail) {
        PharmacyResponseDTO pharmacy = pharmacyService.findPharmacyByUserEmail(userEmail);
        return ResponseEntity.ok(pharmacy);
    }

    @PostMapping("/pharmacy")
    @Operation(summary = "Add new pharmacy ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" ,description = "Created, pharmacy created")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , May be the email or phone numbers already exist")
    })
    public ResponseEntity<String> addPharmacy(@RequestBody PharmacyRequestDTO newPharmacy) {
        String message = pharmacyService.addPharmacy(newPharmacy);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }


    @PutMapping("/id/{pharmacyId}/pharmacy")
    @Operation(summary = "Update pharmacy with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, pharmacy found and updated")
            ,@ApiResponse(responseCode = "404",description = "Not found , No pharmacy with this id")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , May be the email or phone numbers already exist")

    })
    public ResponseEntity<String> updatePharmacy(@RequestBody PharmacyDTO updatedPharmacy, @PathVariable Long pharmacyId) {
        String message = pharmacyService.updatePharmacy(updatedPharmacy, pharmacyId);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/id/{pharmacyId}")
    @Operation(summary = "Delete pharmacy with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, pharmacy found and disabled")
            ,@ApiResponse(responseCode = "404",description = "Not found , No pharmacy with this id")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , The pharmacy you tried to disable is already disabled")
    })
    public ResponseEntity<String> deletePharmacyById(@PathVariable Long pharmacyId) {
        String message = pharmacyService.deletePharmacy(pharmacyId);
        return ResponseEntity.ok(message);
    }
}