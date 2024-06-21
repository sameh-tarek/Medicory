package com.graduationProject.medicory.controller.emergency;

import com.graduationProject.medicory.model.emergency.EmergencyDTO;
import com.graduationProject.medicory.service.emergency.EmergencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code/{code}/emergency")
@RequiredArgsConstructor
public class EmergencyController {
    private final EmergencyService emergencyService;
    @Operation(summary = "Get emergency information", description = "Fetches emergency information based on the provided code.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved emergency information",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmergencyDTO.class))),
            @ApiResponse(responseCode = "404", description = "User with code does not exist", content = @Content),
    })
    @GetMapping
    public ResponseEntity< EmergencyDTO> showEmergencyInfo(@PathVariable String code){
       EmergencyDTO emergencyInfo= emergencyService.getEmergencyInfo(code);
       return new ResponseEntity<>(emergencyInfo, HttpStatus.OK);

    }

}
