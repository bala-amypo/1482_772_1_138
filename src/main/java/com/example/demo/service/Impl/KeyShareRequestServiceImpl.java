package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.KeyShareRequest;
import com.example.demo.repository.KeyShareRequestRepository;

@Service
public class KeyShareRequestServiceImpl 
        implements KeyShareRequestService {

    @Autowired
    private KeyShareRequestRepository repo;

    @Override
    public KeyShareRequest createRequest(KeyShareRequest request) {
        request.setStatus("PENDING");
        request.setRequestedAt(LocalDateTime.now());
        return repo.save(request);
    }

    @Override
    public List<KeyShareRequest> getAllRequests() {
        return repo.findAll();
    }

    @Override
    public KeyShareRequest approveRequest(Long id) {
        KeyShareRequest req = repo.findById(id).orElseThrow();
        req.setStatus("APPROVED");
        return repo.save(req);
    }

    @Override
    public KeyShareRequest rejectRequest(Long id) {
        KeyShareRequest req = repo.findById(id).orElseThrow();
        req.setStatus("REJECTED");
        return repo.save(req);
    }
}
