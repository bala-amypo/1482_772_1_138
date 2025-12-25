package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.Guest;
import com.example.demo.repository.GuestRepository;
import com.example.demo.security.JwtTokenProvider;

import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final GuestRepository guestRepository;
    private final JwtTokenProvider tokenProvider;

    public AuthController(GuestRepository guestRepository,
                          JwtTokenProvider tokenProvider) {
        this.guestRepository = guestRepository;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register")
    public Guest register(@RequestBody RegisterRequest request) {

        if (guestRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Guest g = new Guest();
        g.setFullName(request.getFullName());
        g.setEmail(request.getEmail());
        g.setPhoneNumber(request.getPhoneNumber());
        g.setVerified(true);

        return guestRepository.save(g);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {

        Authentication auth =
            new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword());

        String token = tokenProvider.generateToken(auth);
        return new TokenResponse(token);
    }
}
