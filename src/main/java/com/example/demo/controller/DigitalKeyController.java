package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.DigitalKey;
import com.example.demo.service.DigitalKeyService;

@RestController
@RequestMapping("/api/keys")
public class DigitalKeyController {

    private final DigitalKeyService keyService;

    public DigitalKeyController(DigitalKeyService keyService) {
        this.keyService = keyService;
    }

    @PostMapping("/generate/{bookingId}")
    public DigitalKey generateKey(@PathVariable Long bookingId) {
        return keyService.generateKey(bookingId);
    }

    @GetMapping("/{id}")
    public DigitalKey getKey(@PathVariable Long id) {
        return keyService.getKeyById(id);
    }

    @GetMapping("/guest/{guestId}")
    public List<DigitalKey> getKeysByGuest(@PathVariable Long guestId) {
        return keyService.getKeysByGuestId(guestId);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateKey(@PathVariable Long id) {
        keyService.deactivateKey(id);
    }
}
