package com.sameh.medicory.controller.owner;

import com.sameh.medicory.entity.testsEntities.LabTest;
import com.sameh.medicory.model.tests.LabTestDTO;
import com.sameh.medicory.service.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners/{ownerId}/tests/lab-tests")
public class OwnerTestsController {
    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public List<LabTestDTO> getLabTests(@PathVariable long ownerId){
        return ownerService.getOwnerLabTests(ownerId);
    }

    @GetMapping("/{testId}")
    public LabTestDTO getLabTestById(@PathVariable long ownerId, @PathVariable long testId){
        return ownerService.getOwnerLabTestByTestId(testId, ownerId);
    }


}
