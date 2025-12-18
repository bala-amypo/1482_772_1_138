package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.AccessLog;
import com.example.demo.repository.AccessLogRepository;

@Service
public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository repository;

    public AccessLogServiceImpl(AccessLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccessLog createLog(AccessLog log) {

        if (log.getAction() == null || log.getAction().isEmpty()) {
            throw new IllegalArgumentException("action required");
        }

        return repository.save(log);
    }

    @Override
    public List<AccessLog> getAllLogs() {
        return repository.findAll();
    }
}
