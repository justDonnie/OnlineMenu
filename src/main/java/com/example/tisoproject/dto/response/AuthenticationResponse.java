package com.example.tisoproject.dto.response;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String token,
        String email) {
}
