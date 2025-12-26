package com.example.demo.repository;

import com.example.demo.model.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {

    // REQUIRED exact method name
    List<AccessLog> findByGuestId(Long guestId);

    // REQUIRED exact method name
    List<AccessLog> findByDigitalKeyId(Long keyId);
}
