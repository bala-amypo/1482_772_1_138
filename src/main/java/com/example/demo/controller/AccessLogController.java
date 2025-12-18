package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.AccessLog;
import com.example.demo.service.AccessLogService;

@RestController
public class AccessLogController {

    private final AccessLogService service;

    public AccessLogController(AccessLogService service) {
        this.service = service;
    }

    @PostMapping("/add-access-log")
    public AccessLog addLog(@RequestBody AccessLog log) {
        return service.createLog(log);
    }

    @GetMapping("/show-access-logs")
    public List<AccessLog> showLogs() {
        return service.getAllLogs();
    }
}
