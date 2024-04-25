package com.sameh.medicory.service.auth;

import com.sameh.medicory.entity.usersEntities.User;
import com.sameh.medicory.model.auth.AuthenticationRequest;
import com.sameh.medicory.model.auth.AuthenticationResponse;
import com.sameh.medicory.repository.UserRepository;
import com.sameh.medicory.security.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JWTService jwtService;
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        log.info("user with Email {} wants to login.", authenticationRequest.getEmail());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );

        User user = userRepository.findByEmail(authenticationRequest.getEmail());
        if(user == null){
            throw new UsernameNotFoundException("Not found This user");
        }

        var jwtToken = jwtService.generateToken(authentication);
        log.info("The user has login successfully");
        return AuthenticationResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .token(jwtToken)
                .build();
    }
}
