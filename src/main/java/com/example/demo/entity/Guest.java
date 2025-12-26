package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "guests")
public class Guest {

    @Id
    @GeneratedValue
    private Long id;

    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private Boolean verified = false;
    private Boolean active = true;
    private String role = "ROLE_USER";

    private Timestamp createdAt;

    @PrePersist
    void create() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Boolean getVerified() { return verified; }
    public void setVerified(Boolean verified) { this.verified = verified; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Timestamp getCreatedAt() { return createdAt; }
}
