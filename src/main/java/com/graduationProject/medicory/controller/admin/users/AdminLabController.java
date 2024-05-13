package com.graduationProject.medicory.controller.admin.users;

import com.graduationProject.medicory.model.users.lab.LabDTO;
import com.graduationProject.medicory.model.users.lab.LabRequestDTO;
import com.graduationProject.medicory.model.users.lab.LabResponseDTO;
import com.graduationProject.medicory.service.admin.users.lab.AdminLabService;
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
@RequestMapping("/admin/labs")
@RequiredArgsConstructor
@OpenAPIDefinition(
        info = @Info(
                description = "Admin handle user 'LAB' "
        )
)
public class AdminLabController {
    private final AdminLabService labService;

    @GetMapping("/id/{labId}/lab")
    @Operation(summary = "Get all data of lab found by search with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, lab found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No lab with this id ")
    })
    public ResponseEntity<LabDTO> showAllLabDataById(@PathVariable Long labId) {
        LabDTO lab = labService.showAllLabDataById(labId);
        return ResponseEntity.ok(lab);
    }

    @GetMapping("/name/{labName}")
    @Operation(summary = "Search for lab by its name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, labs found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No labs with this name")
    })
    public ResponseEntity<List<LabResponseDTO>> findLabByName(@PathVariable String labName) {
        List<LabResponseDTO> labs = labService.findLabByName(labName);
        return ResponseEntity.ok(labs);
    }


    @GetMapping("/email/{userEmail}")
    @Operation(summary = "Search for lab by its email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, lab found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No lab with this email")
    })
    public ResponseEntity<LabResponseDTO> findLabByEmail(@PathVariable String userEmail) {
        LabResponseDTO lab = labService.findLabByEmail(userEmail);
        return ResponseEntity.ok(lab);
    }

    @GetMapping("code/{code}")
    @Operation(summary = "Search for lab by its code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, lab found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No lab with this code")
    })
    public ResponseEntity<LabResponseDTO> findLabByCode(@PathVariable String code){
        LabResponseDTO lab = labService.findLabByUserCode(code);
        return ResponseEntity.ok(lab);
    }

    @PostMapping("/lab")
    @Operation(summary = "Add new lab ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" ,description = "Created, lab created")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , May be the email or phone numbers already exist")
    })
    public ResponseEntity<String> addLab(@RequestBody LabRequestDTO newLab) {
        String message = labService.addLab(newLab);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PutMapping("/lab/{labId}")
    @Operation(summary = "Update lab with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, lab found and updated")
            ,@ApiResponse(responseCode = "404",description = "Not found , No lab with this id")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , May be the email or phone numbers already exist")

    })
    public ResponseEntity<String> updateLab(@RequestBody LabDTO updatedLab, @PathVariable Long labId) {
        String message = labService.updateLab(updatedLab, labId);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/lab/{labId}")
    @Operation(summary = "Delete lab with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, lab found and disabled")
            ,@ApiResponse(responseCode = "404",description = "Not found , No lab with this id")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , The lab you tried to disable is already disabled")
    })
    public ResponseEntity<String> deleteById(@PathVariable Long labId) {
        String message = labService.deleteLab(labId);
        return ResponseEntity.ok(message);
    }
}
