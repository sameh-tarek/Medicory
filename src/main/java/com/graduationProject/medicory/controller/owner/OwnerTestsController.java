package com.graduationProject.medicory.controller.owner;

import com.graduationProject.medicory.model.tests.ImagingTestResponseDTO;
import com.graduationProject.medicory.model.tests.LabTestResponseDTO;
import com.graduationProject.medicory.service.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners/{userCode}/tests")
public class OwnerTestsController {
    @Autowired
    private OwnerService ownerService;

    @GetMapping("/lab-tests")
    public List<LabTestResponseDTO> getLabTests(@PathVariable String userCode){
        return ownerService.getOwnerLabTests(userCode);
    }

    @GetMapping("/lab-tests/{testId}")
    public LabTestResponseDTO getLabTestById(@PathVariable String userCode, @PathVariable long testId){
        return ownerService.getOwnerLabTestByTestId(testId, userCode);
    }

    @GetMapping("/imaging-tests")
    public List<ImagingTestResponseDTO> getImagingTests(@PathVariable String userCode){
        return ownerService.getOwnerImagingTests(userCode);
    }

    @GetMapping("/imaging-tests/{testId}")
    public ImagingTestResponseDTO getImagingTestById(@PathVariable String userCode, @PathVariable long testId){
        return ownerService.getOwnerImagingTestByTestId(testId, userCode);
    }

}
