package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.DigitalKey;

public interface DigitalKeyRepository extends JpaRepository<DigitalKey, Long> {
}
