package com.example.demo.repository;

import com.example.demo.model.DigitalKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DigitalKeyRepository extends JpaRepository<DigitalKey, Long> {

    // REQUIRED exact method name
    Optional<DigitalKey> findByBookingIdAndActiveTrue(Long bookingId);

    // REQUIRED exact method name
    List<DigitalKey> findByBookingGuestId(Long guestId);
}
