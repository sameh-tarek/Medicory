package com.sameh.medicory.controller.owner;

import com.sameh.medicory.entity.testsEntities.LabTest;
import com.sameh.medicory.model.tests.ImagingTestDTO;
import com.sameh.medicory.model.tests.LabTestDTO;
import com.sameh.medicory.service.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners/{ownerId}/tests")
public class OwnerTestsController {
    @Autowired
    private OwnerService ownerService;

    @GetMapping("/lab-tests")
    public List<LabTestDTO> getLabTests(@PathVariable long ownerId){
        return ownerService.getOwnerLabTests(ownerId);
    }

    @GetMapping("/lab-tests/{testId}")
    public LabTestDTO getLabTestById(@PathVariable long ownerId, @PathVariable long testId){
        return ownerService.getOwnerLabTestByTestId(testId, ownerId);
    }

    @GetMapping("/imaging-tests")
    public List<ImagingTestDTO> getImagingTests(@PathVariable long ownerId){
        return ownerService.getOwnerImagingTests(ownerId);
    }

    @GetMapping("/imaging-tests/{testId}")
    public ImagingTestDTO getImagingTestById(@PathVariable long ownerId, @PathVariable long testId){
        return ownerService.getOwnerImagingTestByTestId(testId, ownerId);
    }

}
