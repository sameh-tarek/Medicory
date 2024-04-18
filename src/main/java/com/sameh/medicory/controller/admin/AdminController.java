package com.sameh.medicory.controller.admin;


import com.sameh.medicory.model.users.AdminDTO;
import com.sameh.medicory.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/admins")
@RequiredArgsConstructor

public class AdminController {


    private final AdminService adminService;

    @GetMapping("")
    public ResponseEntity<List<AdminDTO>> showAllAdmins(){
        List<AdminDTO> admins = adminService.showAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping("/id/{adminId}")
    public ResponseEntity<AdminDTO> findAdminById(@PathVariable Long adminId){
        AdminDTO admin = adminService.getAdminById(adminId);
        return new ResponseEntity<>(admin,HttpStatus.OK);
    }

    @GetMapping("/name/{adminName}")
    public ResponseEntity<List<AdminDTO>> findAdminByName(@PathVariable("adminName") String fullName ){
        List<AdminDTO> admins = adminService.getAdminByName(fullName);
        return new ResponseEntity<>(admins,HttpStatus.OK);
    }

    @GetMapping("/email/{userEmail}")
    public ResponseEntity<AdminDTO> findAdminByEmail(@PathVariable String userEmail){
        AdminDTO admin = adminService.getAdminByEmail(userEmail);
        return new ResponseEntity<>(admin,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> insertNewAdmin(@RequestBody AdminDTO admin){
        String message = adminService.addAdmin(admin);
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }

    @PutMapping("/id/{adminId}")
    public ResponseEntity<String> updateAdmin(@PathVariable Long adminId,@RequestBody AdminDTO admin){
        String message = adminService.updateAdmin(admin,adminId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @DeleteMapping("/id/{adminId}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long adminId){
        String message = adminService.deleteAdmin(adminId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
