package com.example.tisoproject.api;

import com.example.tisoproject.dto.request.SignInRequest;
import com.example.tisoproject.dto.response.AuthenticationResponse;
import com.example.tisoproject.service.AuthenticationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Authentication API")
public class AuthenticationApi {
    private final AuthenticationService authenticationService;

    @GetMapping("/signIn")
    public AuthenticationResponse signIn(@RequestBody SignInRequest request){
       return authenticationService.signIn(request);
    }
}