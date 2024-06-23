package com.graduationProject.medicory.controller.admin.users;

import com.graduationProject.medicory.model.users.owner.OwnerRequestDTO;
import com.graduationProject.medicory.model.users.owner.OwnerResponseDTO;
import com.graduationProject.medicory.service.admin.users.owner.AdminOwnerService;
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
@RequestMapping("/admin/owners")
@RequiredArgsConstructor
@OpenAPIDefinition(
        info = @Info(
                description = "Admin handle user 'OWNER' "
        )
)
public class AdminOwnerController {

    private final AdminOwnerService ownerService;

    @GetMapping("/email/{userEmail}")
    @Operation(summary = "Search for owner by its email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, owner found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No owner with this email")
    })
    public ResponseEntity<OwnerResponseDTO> findOwnerByEmail(@PathVariable String userEmail) {
        OwnerResponseDTO owner = ownerService.findOwnerByOwnerEmail(userEmail);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @GetMapping("/code/{userCode}")
    @Operation(summary = "Search for owner by its code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, owner found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No owner with this code")
    })
    public ResponseEntity<OwnerResponseDTO> findOwnerUserCode(@PathVariable String userCode) {
        OwnerResponseDTO owner = ownerService.findOwnerByCode(userCode);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    @Operation(summary = "Search for owner by its name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, owners found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No owners with this name")
    })
    public ResponseEntity<List<OwnerResponseDTO>> findOwnerByName(@PathVariable String name) {
        List<OwnerResponseDTO> owner = ownerService.findOwnersByOwnerName(name);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @GetMapping("/id/{ownerId}")
    @Operation(summary = "Get all data of owner found by search with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, owner found")
            ,@ApiResponse(responseCode = "404",description = "Not found , No owner with this id ")
    })
    public ResponseEntity<OwnerRequestDTO> showAllDataOfOwner(@PathVariable long ownerId) {
        OwnerRequestDTO owner = ownerService
                .showAllDataOfOwnerById(ownerId);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @PostMapping("/owner")
    @Operation(summary = "Add new owner ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" ,description = "Created, owner created")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , May be the email or phone numbers already exist")
    })
    public ResponseEntity<String> addNewOwner(@Valid  @RequestBody OwnerRequestDTO newOwner) {
        String message = ownerService.addNewOwner(newOwner);
        return new ResponseEntity<>(message, HttpStatus.CREATED);

    }

    @PutMapping("/owner/id/{ownerId}")
    @Operation(summary = "Update owner with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, owner found and updated")
            ,@ApiResponse(responseCode = "404",description = "Not found , No owner with this id")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , May be the email or phone numbers already exist")

    })
    public ResponseEntity<String> updateOwner(@PathVariable long ownerId,@Valid @RequestBody OwnerRequestDTO updatedOwner) {
        String message = ownerService.updateOwner(ownerId, updatedOwner);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/id/{ownerId}")
    @Operation(summary = "Delete owner with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "OK, owner found and disabled")
            ,@ApiResponse(responseCode = "404",description = "Not found , No owner with this id")
            ,@ApiResponse(responseCode = "409",description = "Conflict  , The owner you tried to disable is already disabled")
    })
    public ResponseEntity<String> deleteOwner(@PathVariable long ownerId) {
        String message = ownerService.deleteOwnerById(ownerId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
