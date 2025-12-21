package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.RoomBookingEntity;

public interface RoomBookingRepository extends JpaRepository<RoomBookingEntity, Long> {
}
