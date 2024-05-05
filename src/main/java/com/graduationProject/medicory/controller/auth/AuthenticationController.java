package com.graduationProject.medicory.controller.auth;

import com.graduationProject.medicory.model.auth.AuthenticationRequest;
import com.graduationProject.medicory.model.auth.AuthenticationResponse;
import com.graduationProject.medicory.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest){
       return authenticationService.authenticate(authenticationRequest);
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) {
        return authenticationService.sendPasswordResetCode(email);
    }

    @PostMapping("/verify-password-reset-code")
    public ResponseEntity<String> verifyPasswordResetCode(@RequestParam String email, @RequestParam String code) {
        boolean codeVerified = authenticationService.verifyPasswordResetCode(email, code);
        if (codeVerified) {
            return ResponseEntity.ok("Verification code is correct.");
        } else {
            return ResponseEntity.badRequest().body("Invalid verification code.");
        }
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email, @RequestParam String newPassword) throws IllegalAccessException {
        return authenticationService.resetPassword(email, newPassword);
    }
}
