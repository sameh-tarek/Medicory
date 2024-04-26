package com.sameh.medicory.controller.admin.users;

import com.sameh.medicory.model.users.hospital.HospitalDTO;
import com.sameh.medicory.model.users.hospital.HospitalResponseDTO;
import com.sameh.medicory.service.admin.users.AdminHospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hosbitals")
@RequiredArgsConstructor
public class AdminHosbitalController {

    private final AdminHospitalService hospitalService;
    @GetMapping("/id/{hospitalId}")
    public ResponseEntity<HospitalDTO> showAllDataOfHosbital(@PathVariable long hospitalId){
        HospitalDTO hospital = hospitalService.showAllDataOfHospitalById(hospitalId);
        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }
    @GetMapping("/name/{hospitalName}")
    public ResponseEntity<List<HospitalResponseDTO>> findHosbitalsByName(@PathVariable String hospitalName){
       List< HospitalResponseDTO> hospitals = hospitalService.findHospitalByName(hospitalName);
      return ResponseEntity.ok(hospitals);
    }
    @GetMapping("/code/{code}")
    public ResponseEntity<HospitalResponseDTO> findHospitalByCode(@PathVariable String code){
        HospitalResponseDTO hospital = hospitalService.findHospitalByUserCode(code);
        return new ResponseEntity<>(hospital,HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<HospitalResponseDTO> findHospitalByEmail(@PathVariable String email){
        HospitalResponseDTO hospital = hospitalService.findHospitalByEmail(email);
        return new ResponseEntity<>(hospital,HttpStatus.OK);
    }
    @PostMapping("/hospital")
    public ResponseEntity<String> addHospital(@RequestBody HospitalDTO hospital){
        String message = hospitalService.addHospital(hospital);
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }
    @PutMapping("/id/{hospitalId}/hospital")
    public ResponseEntity<String> updateHospital(@PathVariable long hospitalId,@RequestBody HospitalDTO hospital){
        String message = hospitalService.updateHospital(hospitalId,hospital);
        return ResponseEntity.ok(message);
    }
    @DeleteMapping("/id/{hospitalId}")
    public ResponseEntity<String> deleteHospital(@PathVariable Long hospitalId){
        String message =hospitalService.deleteHospital(hospitalId);
        return ResponseEntity.ok(message);

    }
}
