package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    // already used earlier
    boolean existsByEmail(String email);

    // 🔥 REQUIRED FOR YOUR ERROR
    Optional<Guest> findByEmail(String email);
}
