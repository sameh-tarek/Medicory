package com.sameh.medicory.service.auth;

import com.sameh.medicory.model.auth.AuthenticationRequest;
import com.sameh.medicory.model.auth.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    String sendPasswordResetCode(String email);
    boolean verifyPasswordResetCode(String email, String code);
    String resetPassword(String email, String newPassword) throws IllegalAccessException;
}
