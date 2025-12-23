package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AccessLog;
import com.example.demo.repository.AccessLogRepository;

@Service
public class AccessLogServiceImpl implements AccessLogService {

    @Autowired
    private AccessLogRepository repo;

    @Override
    public AccessLog addLog(AccessLog log) {
        log.setAccessedAt(LocalDateTime.now());
        return repo.save(log);
    }

    @Override
    public List<AccessLog> getAllLogs() {
        return repo.findAll();
    }

    @Override
    public List<AccessLog> getLogsByGuest(Long guestId) {
        return repo.findByGuestId(guestId);
    }

    @Override
    public List<AccessLog> getLogsByKey(Long keyId) {
        return repo.findByDigitalKeyId(keyId);
    }
}
