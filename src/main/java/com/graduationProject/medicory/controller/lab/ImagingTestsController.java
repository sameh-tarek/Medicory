package com.graduationProject.medicory.controller.lab;

import com.graduationProject.medicory.model.tests.ImagingTestResponseDTO;
import com.graduationProject.medicory.service.lab.imageTest.LabImageTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lab/imagingTests/")
public class ImagingTestsController {

    final private LabImageTestService labImageTestService;

    @GetMapping("{prescriptionId}")
    ResponseEntity<List<ImagingTestResponseDTO>> getAllImagingTests(@PathVariable Long prescriptionId){
        List<ImagingTestResponseDTO> response = labImageTestService.getAllImageTestsOfPrescription(prescriptionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("active/{prescriptionId}")
    ResponseEntity<List<ImagingTestResponseDTO>> getActiveImagingTests(@PathVariable Long prescriptionId){
        List<ImagingTestResponseDTO> response = labImageTestService.getActiveImageTestsOfPrescription(prescriptionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("result/{imageTestId}")
    ResponseEntity<String> uploadTestResult(@RequestParam("file") MultipartFile file, @PathVariable Long imageTestId) throws IOException {
        String response = labImageTestService.uploadImageTestResult(file,imageTestId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("result/{testId}")
    ResponseEntity<String> deleteTestResult(@PathVariable Long testId) throws IOException {
        String response = labImageTestService.deleteImageTestResult(testId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}