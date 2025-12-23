package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class KeyShareRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private DigitalKey digitalKey;

    @ManyToOne
    private Guest requester;

    @ManyToOne
    private Guest targetGuest;

    private String status; // PENDING, APPROVED, REJECTED

    private LocalDateTime requestedAt;

    public KeyShareRequest() {}

    // getters & setters

    public Long getId() {
        return id;
    }

    public DigitalKey getDigitalKey() {
        return digitalKey;
    }

    public void setDigitalKey(DigitalKey digitalKey) {
        this.digitalKey = digitalKey;
    }

    public Guest getRequester() {
        return requester;
    }

    public void setRequester(Guest requester) {
        this.requester = requester;
    }

    public Guest getTargetGuest() {
        return targetGuest;
    }

    public void setTargetGuest(Guest targetGuest) {
        this.targetGuest = targetGuest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }
}
