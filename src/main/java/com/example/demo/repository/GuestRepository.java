package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.GuestEntity;

public interface GuestRepository extends JpaRepository<GuestEntity, Long> {
    boolean existsByEmail(String email);
}
