package com.graduationProject.medicory.controller.admin.users;

import com.graduationProject.medicory.model.users.lab.LabDTO;
import com.graduationProject.medicory.model.users.lab.LabRequestDTO;
import com.graduationProject.medicory.model.users.lab.LabResponseDTO;
import com.graduationProject.medicory.service.admin.users.AdminLabService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/labs")
@RequiredArgsConstructor
public class AdminLabController {
    private final AdminLabService labService;

    @GetMapping("/id/{labId}/lab")
    public ResponseEntity<LabDTO> showAllLabDataById(@PathVariable Long labId) {
        LabDTO lab = labService.showAllLabDataById(labId);
        return ResponseEntity.ok(lab);
    }

    @GetMapping("/name/{labName}")
    public ResponseEntity<List<LabResponseDTO>> findLabByName(@PathVariable String labName) {
        List<LabResponseDTO> labs = labService.findLabByName(labName);
        return ResponseEntity.ok(labs);
    }


    @GetMapping("/email/{userEmail}")
    public ResponseEntity<LabResponseDTO> findLabByEmail(@PathVariable String userEmail) {
        LabResponseDTO lab = labService.findLabByEmail(userEmail);
        return ResponseEntity.ok(lab);
    }

    @GetMapping("code/{code}")
    public ResponseEntity<LabResponseDTO> findLabByCode(@PathVariable String code){
        LabResponseDTO lab = labService.findLabByUserCode(code);
        return ResponseEntity.ok(lab);
    }

    @PostMapping("/lab")
    public ResponseEntity<String> addLab(@RequestBody LabRequestDTO newLab) {
        String message = labService.addLab(newLab);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PutMapping("/lab/{labId}")
    public ResponseEntity<String> updateLab(@RequestBody LabDTO updatedLab, @PathVariable Long labId) {
        String message = labService.updateLab(updatedLab, labId);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/lab/{labId}")
    public ResponseEntity<String> deleteById(@PathVariable Long labId) {
        String message = labService.deleteLab(labId);
        return ResponseEntity.ok(message);
    }
}
