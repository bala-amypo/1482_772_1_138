package com.example.demo.repository;

import com.example.demo.model.KeyShareRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeyShareRequestRepository extends JpaRepository<KeyShareRequest, Long> {

    // REQUIRED exact method name
    List<KeyShareRequest> findBySharedById(Long guestId);

    // REQUIRED exact method name
    List<KeyShareRequest> findBySharedWithId(Long guestId);
}
