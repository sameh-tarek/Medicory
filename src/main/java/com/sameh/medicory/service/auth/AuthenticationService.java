package com.sameh.medicory.service.auth;

import com.sameh.medicory.model.auth.AuthenticationRequest;
import com.sameh.medicory.model.auth.AuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
