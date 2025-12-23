package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.AccessLog;
import java.util.List;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {

    List<AccessLog> findByGuestId(Long guestId);

    List<AccessLog> findByDigitalKeyId(Long keyId);
}
