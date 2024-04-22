package com.sameh.medicory.controller.admin.users;

import com.sameh.medicory.model.users.DoctorDTO;
import com.sameh.medicory.service.admin.users.AdminDoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/doctors")
public class AdminDoctorController {

    private final AdminDoctorService doctorService;

    @GetMapping("")
    public ResponseEntity<List<DoctorDTO>> findAllDoctors(){
        List<DoctorDTO> doctors =doctorService.findAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/id/{doctorId}")
     public ResponseEntity<DoctorDTO> findDoctorById(@PathVariable Long doctorId){
        DoctorDTO doctor = doctorService.findDoctorById(doctorId);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @GetMapping("/name/{doctorName}")
    public ResponseEntity<List<DoctorDTO>> findDoctorByName(@PathVariable String doctorName){
        List<DoctorDTO> doctors = doctorService.findDoctorbyFullName(doctorName);
        return new ResponseEntity<>(doctors,HttpStatus.OK);

    }

    @GetMapping("/email/{userEmail}")
    public ResponseEntity<DoctorDTO> findDoctorByUserEmail(@PathVariable String userEmail){
        DoctorDTO doctor = doctorService.findDoctoByUserEmail(userEmail);
        return  new ResponseEntity<>(doctor,HttpStatus.OK);
    }

    @PostMapping("/doctor")
    public ResponseEntity<String> insertDoctor(@RequestBody DoctorDTO doctor){
        String message = doctorService.addNewDoctor(doctor);
        return new ResponseEntity<>(message ,HttpStatus.CREATED);

    }

    @PutMapping("/doctor/id/{doctorId}")
    public ResponseEntity<String> updateDoctor(@PathVariable Long  doctorId ,@RequestBody DoctorDTO doctorDTO){
        String message = doctorService.updateDoctor(doctorDTO,doctorId);

        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @DeleteMapping("/doctor/id/{doctorId}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable  Long doctorId){
        String message = doctorService.deleteDoctorById(doctorId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }



}
