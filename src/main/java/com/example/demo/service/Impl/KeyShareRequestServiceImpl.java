package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DigitalKey;
import com.example.demo.model.Guest;
import com.example.demo.model.KeyShareRequest;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.KeyShareRequestService;

import java.time.Instant;
import java.util.List;

public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    private final KeyShareRequestRepository repository;
    private final DigitalKeyRepository digitalKeyRepository;
    private final GuestRepository guestRepository;

    public KeyShareRequestServiceImpl(
            KeyShareRequestRepository repository,
            DigitalKeyRepository digitalKeyRepository,
            GuestRepository guestRepository) {

        this.repository = repository;
        this.digitalKeyRepository = digitalKeyRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public KeyShareRequest createShareRequest(KeyShareRequest request) {

        DigitalKey key = digitalKeyRepository.findById(
                request.getDigitalKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));

        Guest sharedBy = guestRepository.findById(
                request.getSharedBy().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found"));

        Guest sharedWith = guestRepository.findById(
                request.getSharedWith().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found"));

        // ❌ Self-share prevention
        if (sharedBy.getId().equals(sharedWith.getId())) {
            throw new IllegalArgumentException("sharedBy and sharedWith cannot be same");
        }

        // ❌ Share end before start
        if (request.getShareEnd().isBefore(request.getShareStart())) {
            throw new IllegalArgumentException("Share end must be after start");
        }

        // ❌ Recipient must be verified & active
        if (!Boolean.TRUE.equals(sharedWith.getVerified()) ||
            !Boolean.TRUE.equals(sharedWith.getActive())) {
            throw new IllegalArgumentException("Recipient not verified or inactive");
        }

        request.setDigitalKey(key);
        request.setSharedBy(sharedBy);
        request.setSharedWith(sharedWith);
        request.setStatus("PENDING");

        return repository.save(request);
    }

    @Override
    public KeyShareRequest getShareRequestById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Share request not found " + id));
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedBy(Long guestId) {
        return repository.findBySharedById(guestId);
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedWith(Long guestId) {
        return repository.findBySharedWithId(guestId);
    }

    @Override
    public KeyShareRequest updateStatus(Long requestId, String status) {

        KeyShareRequest req = getShareRequestById(requestId);

        if (!status.equals("PENDING") &&
            !status.equals("APPROVED") &&
            !status.equals("REJECTED")) {
            throw new IllegalArgumentException("Invalid status");
        }

        // When approving, ensure share window inside key validity
        if (status.equals("APPROVED")) {
            Instant start = req.getShareStart();
            Instant end = req.getShareEnd();
            DigitalKey key = req.getDigitalKey();

            if (start.isBefore(key.getIssuedAt()) ||
                end.isAfter(key.getExpiresAt())) {
                throw new IllegalArgumentException("Share window outside key validity");
            }
        }

        req.setStatus(status);
        return repository.save(req);
    }
}
