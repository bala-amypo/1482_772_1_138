package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.DigitalKey;

public interface DigitalKeyService {

    DigitalKey generateKey(Long bookingId);

    DigitalKey getKeyById(Long id);

    List<DigitalKey> getKeysByGuestId(Long guestId);

    void deactivateKey(Long id);
}
