package com.example.demo.controller;

import com.example.demo.model.Guest;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.GuestService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final GuestService guestService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(GuestService guestService,
                          JwtTokenProvider jwtTokenProvider) {
        this.guestService = guestService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public Guest register(@RequestBody Guest guest) {
        return guestService.createGuest(guest);
    }

    @PostMapping("/login")
    public String login(@RequestBody Guest guest) {
        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        guest.getEmail(), guest.getPassword());
        return jwtTokenProvider.generateToken(auth);
    }
}
