package com.sameh.medicory.controller.admin.users;

import com.sameh.medicory.model.users.owner.OwnerRequestDTO;
import com.sameh.medicory.model.users.owner.OwnerResponseDTO;
import com.sameh.medicory.service.admin.users.AdminOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin/owners")
@RequiredArgsConstructor
public class AdminOwnerController {

    private  final AdminOwnerService ownerService;

    @GetMapping("/email/{userEmail}")
     public ResponseEntity<OwnerResponseDTO> findOwnerByEmail(@PathVariable String userEmail){
        OwnerResponseDTO owner = ownerService.findOwnerByOwnerEmail(userEmail);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }
    @GetMapping("/code/{userCode}")
    public ResponseEntity<OwnerResponseDTO> findOwnerUserCode(@PathVariable String userCode){
        OwnerResponseDTO owner =ownerService.findOwnerByCode(userCode);
        return new ResponseEntity<>(owner,HttpStatus.OK);
    }
    @GetMapping("name/{name}")
    public  ResponseEntity<List<OwnerResponseDTO>> findOwnerByName(@PathVariable String name){
        List<OwnerResponseDTO> owner = ownerService.findOwnersByOwnerName(name);
        return new ResponseEntity<>(owner,HttpStatus.OK);
    }

    @GetMapping("/id/{ownerId}")
    public ResponseEntity<OwnerRequestDTO> showAllDataOfOwner(@PathVariable long ownerId){
        OwnerRequestDTO owner =ownerService
                .showAllDataOfOwnerById(ownerId);
        return  new ResponseEntity<>(owner,HttpStatus.OK);
    }
    @PostMapping("/owner")
    public ResponseEntity<String> addNewOwner(@RequestBody OwnerRequestDTO newOwner){
        String message= ownerService.addNewOwner(newOwner);
        return  new ResponseEntity<>(message,HttpStatus.CREATED);

    }
    @PutMapping("/owner/id/{ownerId}")
    public ResponseEntity<String> updateOwner(@PathVariable long ownerId,@RequestBody OwnerRequestDTO updatedOwner){
        String message = ownerService.updateOwner(ownerId,updatedOwner);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @DeleteMapping("/id/{ownerId}")
    public ResponseEntity<String> deleteOwner(@PathVariable long ownerId){
        String message = ownerService.deleteOwnerById(ownerId);
        return  new ResponseEntity<>(message,HttpStatus.OK);
    }

}
