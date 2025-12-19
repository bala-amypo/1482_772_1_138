package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.DigitalKey;
import com.example.demo.service.DigitalKeyService;

@RestController
public class DigitalKeyController {

    private final DigitalKeyService digitalKeyService;

    public DigitalKeyController(DigitalKeyService digitalKeyService) {
        this.digitalKeyService = digitalKeyService;
    }

    @PostMapping("/addkey")
    public DigitalKey addKey(@RequestBody DigitalKey digitalKey) {
        return digitalKeyService.createKey(digitalKey);
    }

    @GetMapping("/showkeys")
    public List<DigitalKey> showKeys() {
        return digitalKeyService.getAllKeys();
    }
}
