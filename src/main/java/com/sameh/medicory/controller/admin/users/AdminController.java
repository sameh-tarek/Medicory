package com.sameh.medicory.controller.admin.users;


import com.sameh.medicory.model.users.admin.AdminRequestDTO;
import com.sameh.medicory.model.users.admin.AdminResponseDTO;
import com.sameh.medicory.model.users.doctor.DoctorResponseDTO;
import com.sameh.medicory.service.admin.users.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/admins")
@RequiredArgsConstructor

public class AdminController {


    private final AdminService adminService;
    @GetMapping("/code/{code}")
    public ResponseEntity<AdminResponseDTO> findDoctorByCode(@PathVariable String code) {
        AdminResponseDTO admin = adminService.findAdminByUserCode(code);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<AdminResponseDTO> findAdminByEmail(@PathVariable String email){
        AdminResponseDTO admin = adminService.findAdminByEmail(email);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
    @GetMapping("/name/{fullName}")
    public ResponseEntity<List<AdminResponseDTO>> findAdminsByName(@PathVariable String fullName){
        List<AdminResponseDTO> admins = adminService.findAdminByName(fullName);
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
    @GetMapping("/id/{adminId}/admin")
    public ResponseEntity<AdminRequestDTO> showAllDataOfAdmin(@PathVariable long adminId){
        AdminRequestDTO admin = adminService.showAllAdminDataById(adminId);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<String> addAdmin(@RequestBody AdminRequestDTO newAdmin){
        String message = adminService.addAdmin(newAdmin);
        return new ResponseEntity<>(message, HttpStatus.CREATED);

    }

    @PutMapping("/id/{adminId}/admin")
    public ResponseEntity<String > updateAdmin(@PathVariable long adminId,@RequestBody AdminRequestDTO updatedAdmin){
        String message = adminService.updateAdmin(updatedAdmin,adminId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @DeleteMapping("/id/{adminId}")
    public ResponseEntity<String> deleteAdmin(@PathVariable long adminId){
        String  message = adminService.deleteAdmin(adminId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
