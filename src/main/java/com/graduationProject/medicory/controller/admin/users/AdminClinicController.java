package com.graduationProject.medicory.controller.admin.users;

import com.graduationProject.medicory.model.users.clinic.ClinicDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicRequestDTO;
import com.graduationProject.medicory.model.users.clinic.ClinicResponseDTO;
import com.graduationProject.medicory.service.admin.users.clinic.AdminClinicService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/clinics")
@RequiredArgsConstructor
@OpenAPIDefinition(
        info = @Info(
                description = "Admin handle user 'CLINIC' "
        )
)
public class AdminClinicController {


    private final AdminClinicService clinicService;


    @GetMapping("/code/{code}")
    @Operation(summary = "Search for clinic by its code")
    @ApiResponses(value = {
             @ApiResponse(responseCode = "200" ,description = "OK, Clinic found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No clinic with this code")
    })
    public ResponseEntity<ClinicResponseDTO> findClinicByCode(@PathVariable String code) {
        ClinicResponseDTO clinic = clinicService.findClinicByUserCode(code);
        return new ResponseEntity<>(clinic, HttpStatus.OK);
    }

    @GetMapping("/email/{userEmail}")
    @Operation(summary = "Search for clinic by its email")
    @ApiResponses(value = {
             @ApiResponse(responseCode = "200" ,description = "OK, Clinic found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No clinic with this email")
    })
    public ResponseEntity<ClinicResponseDTO> findClinicByUserEmail(@PathVariable String userEmail) {
        ClinicResponseDTO clinic = clinicService.findClinicByUserEmail(userEmail);
        return new ResponseEntity<>(clinic, HttpStatus.OK);
    }

    @GetMapping("/name/{clinicName}")
    @Operation(summary = "Search for clinic by its name")
    @ApiResponses(value = {
             @ApiResponse(responseCode = "200" ,description = "OK, Clinics found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No clinics with this name")
    })
    public ResponseEntity<List<ClinicResponseDTO>> findClinicByClinicName(@PathVariable String clinicName) {
        List<ClinicResponseDTO> clinics = clinicService.findClinicsByName(clinicName);
        return new ResponseEntity<>(clinics, HttpStatus.OK);
    }

    @GetMapping("/id/{clinicId}/clinic")
    @Operation(summary = "Get all data of clinic found by search with id")
    @ApiResponses(value = {
             @ApiResponse(responseCode = "200" ,description = "OK, Clinic found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No clinic with this id ")
    })
    public ResponseEntity<ClinicDTO> getAllDataOfClinicById(@PathVariable long clinicId) {
       ClinicDTO clinic = clinicService.showAllDataOfClinicByClinicId(clinicId);
       return new ResponseEntity<>(clinic,HttpStatus.OK);
    }

    @PostMapping("/clinic")
    @Operation(summary = "Add new clinic ")
    @ApiResponses(value = {
             @ApiResponse(responseCode = "201" ,description = "Created, Clinic created")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , May be the email or phone numbers already exist")
    })
    public ResponseEntity<String> addClinic(@Valid @RequestBody ClinicRequestDTO newClinic){
        String message = clinicService.addNewClinic(newClinic);
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }
    @PutMapping("/id/{clinicId}/clinic")
    @Operation(summary = "Update clinic with id")
    @ApiResponses(value = {
             @ApiResponse(responseCode = "200" ,description = "OK, Clinic found and updated")
            ,@ApiResponse(responseCode = "404",description = "Not found , No clinic with this id")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , May be the email or phone numbers already exist")

    })
    public ResponseEntity<String> updateClinic(@PathVariable long clinicId,@Valid @RequestBody ClinicDTO updatedClinic){
        String message = clinicService.updateClinic(updatedClinic,clinicId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @DeleteMapping("/id/{clinicId}")
    @Operation(summary = "Delete clinic with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, Clinic found and disabled")
            ,@ApiResponse(responseCode = "404",description = "Not found , No clinic with this id")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , The clinic you tried to disable is already disabled")
    })
    public ResponseEntity<String> deleteClinic(@PathVariable long clinicId){
        String  message = clinicService.deleteClinicById(clinicId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

}
