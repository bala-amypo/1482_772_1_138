package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.AccessLog;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
}
