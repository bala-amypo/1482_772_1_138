package com.example.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    public String generateToken(Authentication authentication) {
        return "jwt-token";
    }
}
