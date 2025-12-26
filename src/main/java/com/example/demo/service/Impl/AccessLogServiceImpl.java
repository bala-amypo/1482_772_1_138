package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.AccessLogService;
import java.time.Instant;
import java.util.List;

public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository repo;
    private final DigitalKeyRepository keyRepo;
    private final GuestRepository guestRepo;
    private final KeyShareRequestRepository shareRepo;

    public AccessLogServiceImpl(AccessLogRepository repo,
                                DigitalKeyRepository keyRepo,
                                GuestRepository guestRepo,
                                KeyShareRequestRepository shareRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
        this.guestRepo = guestRepo;
        this.shareRepo = shareRepo;
    }

    public AccessLog createLog(AccessLog log) {
        if (log.getAccessTime().isAfter(Instant.now()))
            throw new IllegalArgumentException("future");

        DigitalKey key = keyRepo.findById(log.getDigitalKey().getId()).orElseThrow();
        log.setResult(key.getActive() ? "SUCCESS" : "DENIED");
        return repo.save(log);
    }

    public List<AccessLog> getLogsForGuest(Long id) {
        return repo.findByGuestId(id);
    }

    public List<AccessLog> getLogsForKey(Long id) {
        return repo.findByDigitalKeyId(id);
    }
}
