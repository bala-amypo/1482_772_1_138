package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.KeyShareRequest;

public interface KeyShareRequestRepository 
        extends JpaRepository<KeyShareRequest, Long> {
}
