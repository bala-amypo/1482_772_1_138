package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.service.KeyShareRequestService;

@RestController
public class KeyShareRequestController {

    private final KeyShareRequestService service;

    public KeyShareRequestController(KeyShareRequestService service) {
        this.service = service;
    }

    @PostMapping("/add-key-request")
    public KeyShareRequest addRequest(@RequestBody KeyShareRequest request) {
        return service.createRequest(request);
    }

    @GetMapping("/show-key-requests")
    public List<KeyShareRequest> showRequests() {
        return service.getAllRequests();
    }
}
