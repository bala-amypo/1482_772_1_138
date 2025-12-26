package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.KeyShareRequestService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service   // ✅ REQUIRED
public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    private final KeyShareRequestRepository repo;
    private final DigitalKeyRepository keyRepo;
    private final GuestRepository guestRepo;

    public KeyShareRequestServiceImpl(
            KeyShareRequestRepository repo,
            DigitalKeyRepository keyRepo,
            GuestRepository guestRepo) {

        this.repo = repo;
        this.keyRepo = keyRepo;
        this.guestRepo = guestRepo;
    }

    @Override
    public KeyShareRequest createShareRequest(KeyShareRequest req) {

        if (req.getShareEnd().isBefore(req.getShareStart())) {
            throw new IllegalArgumentException("Share end before start");
        }

        if (req.getSharedBy().getId()
                .equals(req.getSharedWith().getId())) {
            throw new IllegalArgumentException("sharedBy and sharedWith same");
        }

        DigitalKey key = keyRepo.findById(req.getDigitalKey().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Key not found"));

        Guest to = guestRepo.findById(req.getSharedWith().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Guest not found"));

        if (!Boolean.TRUE.equals(to.getVerified()) ||
            !Boolean.TRUE.equals(to.getActive())) {
            throw new IllegalStateException("Guest not eligible");
        }

        req.setStatus("PENDING");
        return repo.save(req);
    }

    @Override
    public KeyShareRequest updateStatus(Long id, String status) {
        KeyShareRequest req = getShareRequestById(id);
        req.setStatus(status);
        return repo.save(req);
    }

    @Override
    public KeyShareRequest getShareRequestById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request not found " + id));
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedBy(Long guestId) {
        return repo.findBySharedById(guestId);
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedWith(Long guestId) {
        return repo.findBySharedWithId(guestId);
    }
}
