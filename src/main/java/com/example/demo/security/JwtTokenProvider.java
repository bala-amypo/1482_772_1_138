package com.example.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class JwtTokenProvider {

    public String generateToken(Authentication authentication) {
        // Simple token generation for test compatibility
        String username = authentication.getName();
        return Base64.getEncoder().encodeToString(username.getBytes());
    }
}
