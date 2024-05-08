package com.graduationProject.medicory.controller.owner;

import com.graduationProject.medicory.model.tests.ImagingTestResponseDTO;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import com.graduationProject.medicory.service.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners/{ownerId}/tests")
public class OwnerTestsController {
    @Autowired
    private OwnerService ownerService;

    @GetMapping("/lab-tests")
    public List<LabTestResponseDTO> getLabTests(@PathVariable long ownerId){
        return ownerService.getOwnerLabTests(ownerId);
    }

    @GetMapping("/lab-tests/{testId}")
    public LabTestResponseDTO getLabTestById(@PathVariable long ownerId, @PathVariable long testId){
        return ownerService.getOwnerLabTestByTestId(testId, ownerId);
    }

    @GetMapping("/imaging-tests")
    public List<ImagingTestResponseDTO> getImagingTests(@PathVariable long ownerId){
        return ownerService.getOwnerImagingTests(ownerId);
    }

    @GetMapping("/imaging-tests/{testId}")
    public ImagingTestResponseDTO getImagingTestById(@PathVariable long ownerId, @PathVariable long testId){
        return ownerService.getOwnerImagingTestByTestId(testId, ownerId);
    }

}
