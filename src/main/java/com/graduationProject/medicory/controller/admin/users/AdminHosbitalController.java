package com.graduationProject.medicory.controller.admin.users;

import com.graduationProject.medicory.model.users.hospital.HospitalDTO;
import com.graduationProject.medicory.model.users.hospital.HospitalRequestDTO;
import com.graduationProject.medicory.model.users.hospital.HospitalResponseDTO;
import com.graduationProject.medicory.service.admin.users.hospital.AdminHospitalService;
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
@RequestMapping("/admin/hosbitals")
@RequiredArgsConstructor
@OpenAPIDefinition(
        info = @Info(
                description = "Admin handle user 'HOSPITAL' "
        )
)
public class AdminHosbitalController {

    private final AdminHospitalService hospitalService;
    @GetMapping("/id/{hospitalId}")
    @Operation(summary = "Get all data of hospital found by search with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, hospital found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No hospital with this id ")
    })
    public ResponseEntity<HospitalDTO> showAllDataOfHosbital(@PathVariable long hospitalId){
        HospitalDTO hospital = hospitalService.showAllDataOfHospitalById(hospitalId);
        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }
    @GetMapping("/name/{hospitalName}")
    @Operation(summary = "Search for hospital by its name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, hospitals found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No hospitals with this name")
    })
    public ResponseEntity<List<HospitalResponseDTO>> findHosbitalsByName(@PathVariable String hospitalName){
       List< HospitalResponseDTO> hospitals = hospitalService.findHospitalByName(hospitalName);
      return ResponseEntity.ok(hospitals);
    }
    @GetMapping("/code/{code}")
    @Operation(summary = "Search for hospital by its code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, hospital found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No hospital with this code")
    })
    public ResponseEntity<HospitalResponseDTO> findHospitalByCode(@PathVariable String code){
        HospitalResponseDTO hospital = hospitalService.findHospitalByUserCode(code);
        return new ResponseEntity<>(hospital,HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    @Operation(summary = "Search for hospital by its email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, hospital found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No hospital with this email")
    })
    public ResponseEntity<HospitalResponseDTO> findHospitalByEmail(@PathVariable String email){
        HospitalResponseDTO hospital = hospitalService.findHospitalByEmail(email);
        return new ResponseEntity<>(hospital,HttpStatus.OK);
    }
    @PostMapping("/hospital")
    @Operation(summary = "Add new hospital ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" ,description = "Created, hospital created")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , May be the email or phone numbers already exist")
    })
    public ResponseEntity<String> addHospital(@RequestBody HospitalRequestDTO hospital){
        String message = hospitalService.addHospital(hospital);
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }
    @PutMapping("/id/{hospitalId}/hospital")
    @Operation(summary = "Update hospital with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, hospital found and updated")
            ,@ApiResponse(responseCode = "404",description = "Not found , No hospital with this id")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , May be the email or phone numbers already exist")

    })
    public ResponseEntity<String> updateHospital(@PathVariable long hospitalId,@RequestBody HospitalDTO hospital){
        String message = hospitalService.updateHospital(hospitalId,hospital);
        return ResponseEntity.ok(message);
    }
    @DeleteMapping("/id/{hospitalId}")
    @Operation(summary = "Delete hospital with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, hospital found and disabled")
            ,@ApiResponse(responseCode = "404",description = "Not found , No hospital with this id")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , The hospital you tried to disable is already disabled")
    })
    public ResponseEntity<String> deleteHospital(@PathVariable Long hospitalId){
        String message =hospitalService.deleteHospital(hospitalId);
        return ResponseEntity.ok(message);

    }
}
