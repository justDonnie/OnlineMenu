package com.example.tisoproject.service;

import com.example.tisoproject.dto.request.SignInRequest;
import com.example.tisoproject.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse signIn(SignInRequest signInRequest);
}
