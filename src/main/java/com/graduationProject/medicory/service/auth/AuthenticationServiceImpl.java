package com.graduationProject.medicory.service.auth;

import com.graduationProject.medicory.entity.usersEntities.User;
import com.graduationProject.medicory.model.auth.AuthenticationRequest;
import com.graduationProject.medicory.model.auth.AuthenticationResponse;
import com.graduationProject.medicory.repository.usersRepositories.UserRepository;
import com.graduationProject.medicory.security.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final JavaMailSender emailSender;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.password-reset.code-length}")
    private int codeLength;

    @Value("${app.password-reset.code-expiration-minutes}")
    private int codeExpirationMinutes;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        log.info("User with Email {} wants to login.", authenticationRequest.getEmail());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );

        User user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String jwtToken = jwtService.generateToken(authentication);
        log.info("User has logged in successfully");
        return AuthenticationResponse.builder()
                .id(user.getId())
                .code(user.getCode())
                .email(user.getEmail())
                .token(jwtToken)
                .role(user.getRole())
                .build();
    }

    @Override
    public String sendPasswordResetCode(String email) {
        log.info("This user with email {}, want to rest password", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String resetCode = generateRandomCode(codeLength);
        user.setResetPasswordCode(resetCode);
        user.setResetPasswordCodeExpiry(codeExpirationMinutes);
        user.setResetPasswordCodeVerified(false);
        userRepository.save(user);

        sendResetPasswordEmail(email, resetCode);
        log.info("Code Sent for Email Successfully");
        return "Password reset code sent to your email.";
    }

    @Override
    public boolean verifyPasswordResetCode(String email, String code) {
        log.info("verify this reset password code: {} that sent for Email {}", code, email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.getResetPasswordCode() != null &&
                user.getResetPasswordCode().equals(code) &&
                user.getResetPasswordCodeExpiry() != null &&
                LocalDateTime.now().isBefore(user.getResetPasswordCodeExpiry())){
            user.setResetPasswordCodeVerified(true);
            userRepository.save(user);
            return true;
        }

        return false;
    }

    @Override
    public String resetPassword(String email, String newPassword) throws IllegalAccessException {
        log.info("user with email {}, want to reset password", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if(!user.isResetPasswordCodeVerified()){
            throw new IllegalAccessException("You can't rest password, " +
                    "because you not verify the code That sent for You on Gmail.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        log.info("Password updated Successfully!");
        return "Password reset successfully.";
    }

    private String generateRandomCode(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private void sendResetPasswordEmail(String email, String code) {
        String emailBody = "Your password reset code is: " + code;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Code");
        message.setText(emailBody);
        emailSender.send(message);
    }
}
