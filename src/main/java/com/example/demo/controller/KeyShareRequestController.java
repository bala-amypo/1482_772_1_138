package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.KeyShareRequest;
import com.example.demo.service.KeyShareRequestService;

@RestController
@RequestMapping("/api/key-share")
@CrossOrigin
public class KeyShareRequestController {

    @Autowired
    private KeyShareRequestService service;

    @PostMapping("/request")
    public KeyShareRequest create(@RequestBody KeyShareRequest request) {
        return service.createRequest(request);
    }

    @GetMapping("/all")
    public List<KeyShareRequest> getAll() {
        return service.getAllRequests();
    }

    @PutMapping("/{id}/approve")
    public KeyShareRequest approve(@PathVariable Long id) {
        return service.approveRequest(id);
    }

    @PutMapping("/{id}/reject")
    public KeyShareRequest reject(@PathVariable Long id) {
        return service.rejectRequest(id);
    }
}
