package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.DigitalKey;

public interface DigitalKeyRepository extends JpaRepository<DigitalKey, Long> {

    DigitalKey findByBookingIdAndActiveTrue(Long bookingId);

    List<DigitalKey> findByBookingGuestId(Long guestId);
}
