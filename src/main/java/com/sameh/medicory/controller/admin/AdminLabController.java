package com.sameh.medicory.controller.admin;

import com.sameh.medicory.model.users.LabDTO;
import com.sameh.medicory.service.admin.AdminLabService;
import com.sameh.medicory.service.lab.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/labs")
public class AdminLabController {
    @Autowired
   private AdminLabService labService;
    @GetMapping("")
    public ResponseEntity< List<LabDTO>> showAllLabs(){
       List<LabDTO> labs = labService.showAllLabs();
       return ResponseEntity.ok(labs);
    }
    @GetMapping("/labId/{labId}")
    public ResponseEntity<LabDTO> findLabById(@PathVariable Long labId){

        LabDTO lab= labService.findLabById(labId);
        return ResponseEntity.ok(lab);
    }
    @GetMapping("/labName/{labName}")
    public ResponseEntity<List<LabDTO>> findLabByName(@PathVariable String labName){
        List<LabDTO> labs = labService.findLabByName(labName);
       return ResponseEntity.ok(labs);
    }
    @GetMapping("/userEmail/{userEmail}")
    public ResponseEntity<LabDTO> findLabByEmail(@PathVariable String userEmail){
        LabDTO lab = labService.findLabByEmail(userEmail);
        return ResponseEntity.ok(lab);
    }
    @PostMapping("/lab")
    public ResponseEntity< String> addLab(@RequestBody LabDTO newLab){

       String message=  labService.addLab(newLab);
       return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    @PutMapping("/lab/{labId}")
    public ResponseEntity<String> updateLab(@RequestBody LabDTO updatedLab,@PathVariable Long labId){
        String message= labService.updateLab(updatedLab,labId);
       return ResponseEntity.ok(message);
    }
    @DeleteMapping("/lab/{labId}")
    public ResponseEntity<String> deleteById(@PathVariable Long labId){

       String message =labService.deleteLab(labId);
       return ResponseEntity.ok(message);
    }

}
