package com.example.tisoproject.security.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.tisoproject.models.User;
import com.example.tisoproject.repository.UserRepository;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.webjars.NotFoundException;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.substring(7);
            if (StringUtils.hasText(token)) {
                try {
                    String email = jwtService.validateToken(token);
                    User user = userRepository.getUserByEmail(email)
                            .orElseThrow(() -> new NotFoundException(
                                    "User with email " + email + " not exists!!!"
                            ));
                    SecurityContextHolder.getContext()
                            .setAuthentication(
                                    new UsernamePasswordAuthenticationToken(
                                            user.getUsername(),
                                            null,
                                            user.getAuthorities()
                                    )
                            );
                } catch (JWTVerificationException e) {
                    response
                            .sendError
                                    (HttpServletResponse.SC_BAD_REQUEST, "Token Error");
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
