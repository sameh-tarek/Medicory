package com.graduationProject.medicory.controller.admin.users;

import com.graduationProject.medicory.model.users.doctor.DoctorDTO;
import com.graduationProject.medicory.model.users.doctor.DoctorRequestDTO;
import com.graduationProject.medicory.model.users.doctor.DoctorResponseDTO;
import com.graduationProject.medicory.service.admin.users.doctor.AdminDoctorService;
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
@RequiredArgsConstructor
@RequestMapping("/admin/doctors")
@OpenAPIDefinition(
        info = @Info(
                description = "Admin handle user 'Doctor' "
        )
)
public class AdminDoctorController {

    private final AdminDoctorService doctorService;


    @GetMapping("/code/{code}")
    @Operation(summary = "Search for doctor by its code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, doctor found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No doctor with this code")
    })
    public ResponseEntity<DoctorResponseDTO> findDoctorByCode(@PathVariable String code) {
        DoctorResponseDTO doctor = doctorService.findDoctorByUserCode(code);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @GetMapping("/email/{userEmail}")
    @Operation(summary = "Search for doctor by its email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, doctor found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No doctor with this email")
    })
    public ResponseEntity<DoctorResponseDTO> findDoctorByUserEmai(@PathVariable String userEmail) {
        DoctorResponseDTO doctor = doctorService.findDoctoByUserEmail(userEmail);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @GetMapping("/name/{doctorName}")
    @Operation(summary = "Search for doctor by its name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, doctors found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No doctors with this name")
    })
    public ResponseEntity<List<DoctorResponseDTO>> findDoctorsByName(@PathVariable String doctorName) {
      List< DoctorResponseDTO> doctors = doctorService.findDoctorbyFullName(doctorName);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }


    @GetMapping("/id/{doctorId}/doctor")
    @Operation(summary = "Get all data of doctor found by search with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, doctor found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No doctor with this id ")
    })
    public ResponseEntity<DoctorDTO> getAllDataOfDoctorById(@PathVariable long doctorId) {
        DoctorDTO doctor = doctorService.showAllDoctorDataById(doctorId);
        return new ResponseEntity<>(doctor,HttpStatus.OK);
    }

    @PostMapping("/doctor")
    @Operation(summary = "Add new doctor ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" ,description = "Created, doctor created")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , May be the email or phone numbers already exist")
    })
   public ResponseEntity<String> addDoctor(@RequestBody DoctorRequestDTO newDoctor){
        String message = doctorService.addNewDoctor(newDoctor);
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }
    @PutMapping("/id/{doctorId}/doctor")
    @Operation(summary = "Update doctor with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, doctor found and updated")
            ,@ApiResponse(responseCode = "404",description = "Not found , No doctor with this id")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , May be the email or phone numbers already exist")

    })
    public ResponseEntity<String> updateDoctor(@RequestBody DoctorDTO updatedDoctor, @PathVariable long doctorId){
        String message = doctorService.updateDoctor(updatedDoctor,doctorId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @DeleteMapping("/id/{doctorId}")
    @Operation(summary = "Delete doctor with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, doctor found and disabled")
            ,@ApiResponse(responseCode = "404",description = "Not found , No doctor with this id")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , The doctor you tried to disable is already disabled")
    })
    public ResponseEntity<String> deleteDoctor(@PathVariable long doctorId){
        String  message = doctorService.deleteDoctorById(doctorId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
