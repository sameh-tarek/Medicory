package com.graduationProject.medicory.controller.admin.users;


import com.graduationProject.medicory.model.users.admin.AdminDTO;
import com.graduationProject.medicory.model.users.admin.AdminRequestDTO;
import com.graduationProject.medicory.model.users.admin.AdminResponseDTO;
import com.graduationProject.medicory.service.admin.users.admin.AdminService;
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
@RequestMapping("/admin/admins")
@RequiredArgsConstructor
@OpenAPIDefinition(
        info = @Info(
                description = "Admin handle user 'ADMIN' "
        )
)
public class AdminController {


    private final AdminService adminService;
    @GetMapping("/code/{code}")
    @Operation(summary = "Search for admin by its code")
    @ApiResponses(value = {
             @ApiResponse(responseCode = "200" ,description = "OK, Admin found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No admin with this code")
    })
    public ResponseEntity<AdminResponseDTO> findDoctorByCode(@PathVariable String code) {
        AdminResponseDTO admin = adminService.findAdminByUserCode(code);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    @Operation(summary = "Search for admin by its email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, admin found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No admin with this email")
    })
    public ResponseEntity<AdminResponseDTO> findAdminByEmail(@PathVariable String email){
        AdminResponseDTO admin = adminService.findAdminByEmail(email);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
    @GetMapping("/name/{fullName}")
    @Operation(summary = "Search for admin by its name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, admins found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No admins with this name")
    })
    public ResponseEntity<List<AdminResponseDTO>> findAdminsByName(@PathVariable String fullName){
        List<AdminResponseDTO> admins = adminService.findAdminByName(fullName);
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
    @GetMapping("/id/{adminId}/admin")
    @Operation(summary = "Get all data of admin found by search with id")
    @ApiResponses(value = {
             @ApiResponse(responseCode = "200" ,description = "OK, admin found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No admin with this id ")
    })
    public ResponseEntity<AdminDTO> showAllDataOfAdmin(@PathVariable long adminId){
        AdminDTO admin = adminService.showAllAdminDataById(adminId);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
    @PostMapping("")
    @Operation(summary = "Add new admin ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" ,description = "Created, admin created")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , May be the email or phone numbers already exist")
    })
    public ResponseEntity<String> addAdmin(@RequestBody AdminRequestDTO newAdmin){
        String message = adminService.addAdmin(newAdmin);
        return new ResponseEntity<>(message, HttpStatus.CREATED);

    }

    @PutMapping("/id/{adminId}/admin")
    @Operation(summary = "Update admin with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, admin found and updated")
            ,@ApiResponse(responseCode = "404",description = "Not found , No admin with this id")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , May be the email or phone numbers already exist")

    })
    public ResponseEntity<String > updateAdmin(@PathVariable long adminId,@RequestBody AdminDTO updatedAdmin){
        String message = adminService.updateAdmin(updatedAdmin,adminId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @DeleteMapping("/id/{adminId}")
    @Operation(summary = "Delete admin with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, admin found and disabled")
            ,@ApiResponse(responseCode = "404",description = "Not found , No admin with this id")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , The admin you tried to disable is already disabled")
    })
    public ResponseEntity<String> deleteAdmin(@PathVariable long adminId){
        String  message = adminService.deleteAdmin(adminId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
