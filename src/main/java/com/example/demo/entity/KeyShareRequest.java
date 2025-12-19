package com.example.demo.model;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "key_share_request")
public class KeyShareRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Digital key being shared
    @ManyToOne
    @JoinColumn(name = "digital_key_id", nullable = false)
    private DigitalKey digitalKey;

    // Guest who receives the key
    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    private GuestEntity guest;

    private String status = "PENDING"; // PENDING / APPROVED / REJECTED

    private Timestamp createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public KeyShareRequest() {}

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public DigitalKey getDigitalKey() { return digitalKey; }
    public void setDigitalKey(DigitalKey digitalKey) {
        this.digitalKey = digitalKey;
    }

    public GuestEntity getGuest() { return guest; }
    public void setGuest(GuestEntity guest) {
        this.guest = guest;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getCreatedAt() { return createdAt; }
}
