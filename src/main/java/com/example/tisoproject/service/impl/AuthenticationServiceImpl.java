package com.example.tisoproject.service.impl;

import com.example.tisoproject.dto.request.SignInRequest;
import com.example.tisoproject.dto.response.AuthenticationResponse;
import com.example.tisoproject.exceptions.BadCredentialException;
import com.example.tisoproject.exceptions.NotFoundException;
import com.example.tisoproject.models.User;
import com.example.tisoproject.repository.UserRepository;
import com.example.tisoproject.security.jwt.JwtService;
import com.example.tisoproject.service.AuthenticationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.getEmail()).orElseThrow(
                () -> new NotFoundException("There are no any users !!!"));
        if (signInRequest.getEmail().isBlank()) {
            throw new BadCredentialException("Email is blanked!!!");
        }
        if (passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialException("Wrong password!!!");
        }
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .email(user.getEmail())
                .build();
    }

    @PostConstruct
    public void initSaveAdmin() {
        log.info("Admin is creating...");
        User user = User.builder()
                .firstName("admin")
                .lastName("admins lastName")
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("admin123"))
                .build();
        if (userRepository.getUserByEmail(user.getEmail()).isEmpty()) {
            userRepository.save(user);
            log.info("Admin successfully created and saved");
        }
    }
}
