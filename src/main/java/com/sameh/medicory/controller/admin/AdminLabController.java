package com.sameh.medicory.controller.admin;

import com.sameh.medicory.model.users.LabDTO;
import com.sameh.medicory.service.admin.AdminLabService;
import com.sameh.medicory.service.lab.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/labs")
public class AdminLabController {
    @Autowired
   private AdminLabService labService;
    @GetMapping("")
    public List<LabDTO> showAllLabs(){
        List<LabDTO> lists = labService.showAllLabs();
       // System.out.println(lists.get(0).getUser());
        return lists;
    }
    @GetMapping("/labId/{labId}")
    public LabDTO findLabById(@PathVariable Long labId){
        return labService.findLabById(labId);
    }
    @GetMapping("/labName/{labName}")
    public List<LabDTO> findLabByName(@PathVariable String labName){
        return labService.findLabByName(labName);
    }
    @GetMapping("/userEmail/{userEmail}")
    public LabDTO findLabByEmail(@PathVariable String userEmail){
        return labService.findLabByEmail(userEmail);
    }
    @PostMapping("/lab")
    public String addLab(@RequestBody LabDTO newLab){
        return labService.addLab(newLab);
    }
    @PutMapping("/{labId}/changedLab")
    public String updateLab(@RequestBody LabDTO updatedLab,@PathVariable Long labId){
        return labService.updateLab(updatedLab,labId);
    }
    @DeleteMapping("/{labId}")
    public String deleteById(@PathVariable Long labId){
        return labService.deleteLab(labId);
    }

}
