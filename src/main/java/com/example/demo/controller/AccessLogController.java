package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.AccessLog;
import com.example.demo.service.AccessLogService;

@RestController
@RequestMapping("/api/access-log")
@CrossOrigin
public class AccessLogController {

    @Autowired
    private AccessLogService service;

    @PostMapping("/add")
    public AccessLog addLog(@RequestBody AccessLog log) {
        return service.addLog(log);
    }

    @GetMapping("/all")
    public List<AccessLog> getAllLogs() {
        return service.getAllLogs();
    }

    @GetMapping("/guest/{guestId}")
    public List<AccessLog> getLogsByGuest(@PathVariable Long guestId) {
        return service.getLogsByGuest(guestId);
    }

    @GetMapping("/key/{keyId}")
    public List<AccessLog> getLogsByKey(@PathVariable Long keyId) {
        return service.getLogsByKey(keyId);
    }
}
