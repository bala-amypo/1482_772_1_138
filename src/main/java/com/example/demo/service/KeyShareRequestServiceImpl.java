package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.repository.KeyShareRequestRepository;

@Service
public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    private final KeyShareRequestRepository repository;

    public KeyShareRequestServiceImpl(KeyShareRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public KeyShareRequest createRequest(KeyShareRequest request) {

        if (request.getDigitalKey() == null) {
            throw new IllegalArgumentException("digital key required");
        }

        return repository.save(request);
    }

    @Override
    public List<KeyShareRequest> getAllRequests() {
        return repository.findAll();
    }
}
