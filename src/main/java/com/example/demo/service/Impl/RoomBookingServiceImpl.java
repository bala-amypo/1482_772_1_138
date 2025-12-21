package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.RoomBookingEntity;
import com.example.demo.repository.RoomBookingRepository;

@Service
public class RoomBookingServiceImpl implements RoomBookingService {

    private final RoomBookingRepository roomBookingRepository;

    public RoomBookingServiceImpl(RoomBookingRepository roomBookingRepository) {
        this.roomBookingRepository = roomBookingRepository;
    }

    @Override
    public RoomBookingEntity createBooking(RoomBookingEntity booking) {
        return roomBookingRepository.save(booking);
    }

    @Override
    public List<RoomBookingEntity> getAllBookings() {
        return roomBookingRepository.findAll();
    }

    @Override
    public RoomBookingEntity getBookingById(Long id) {
        return roomBookingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Room booking not found with id: " + id));
    }
}
