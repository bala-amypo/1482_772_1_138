package com.example.demo.service.impl;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.KeyShareRequestService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    private final KeyShareRequestRepository repository;

    public KeyShareRequestServiceImpl(KeyShareRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public KeyShareRequest createShareRequest(KeyShareRequest request) {
        if (!request.getShareEnd().after(request.getShareStart())) {
            throw new IllegalArgumentException("Share end must be after start");
        }
        if (request.getSharedBy().getId()
                .equals(request.getSharedWith().getId())) {
            throw new IllegalArgumentException("sharedBy and sharedWith cannot be same");
        }
        return repository.save(request);
    }

    @Override
    public KeyShareRequest updateStatus(Long requestId, String status) {
        KeyShareRequest req = getShareRequestById(requestId);
        req.setStatus(status);
        return repository.save(req);
    }

    @Override
    public KeyShareRequest getShareRequestById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Share request not found"));
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedBy(Long guestId) {
        return repository.findBySharedById(guestId);
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedWith(Long guestId) {
        return repository.findBySharedWithId(guestId);
    }
}
