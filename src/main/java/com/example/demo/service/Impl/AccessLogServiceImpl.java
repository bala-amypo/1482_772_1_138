package com.example.demo.service.impl;

import com.example.demo.model.AccessLog;
import com.example.demo.repository.AccessLogRepository;
import com.example.demo.service.AccessLogService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository repository;

    public AccessLogServiceImpl(AccessLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccessLog createLog(AccessLog log) {
        if (log.getAccessTime().after(new Timestamp(System.currentTimeMillis()))) {
            throw new IllegalArgumentException("future access not allowed");
        }
        log.setResult("SUCCESS");
        log.setReason("Access granted");
        return repository.save(log);
    }

    @Override
    public List<AccessLog> getLogsForKey(Long keyId) {
        return repository.findByDigitalKeyId(keyId);
    }

    @Override
    public List<AccessLog> getLogsForGuest(Long guestId) {
        return repository.findByGuestId(guestId);
    }
}
