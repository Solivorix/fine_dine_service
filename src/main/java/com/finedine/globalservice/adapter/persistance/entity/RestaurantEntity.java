package com.finedine.globalservice.adapter.persistance.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;

@Entity
@Data
@Table(name = "restaurants")
public class RestaurantEntity {

    @Id
    @Column(name = "restaurant_id")
    private String restId;

    @Column(name = "restaurant_name")
    private String name;

    @Column(name = "gst_number")
    private String gstNo;
    @Lob
    private String address;

    @Column(name = "primary_email")
    private String primaryEmailId;

    @Column(name = "primary_contact_number")
    private String primaryContactNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime modifiedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String modifiedBy;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.restId = generateHashId();
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedAt = LocalDateTime.now();
    }

    private String generateHashId() {
        try {
            String dataToHash = name + gstNo + createdAt.toString(); // Combine key attributes
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes); // Encode to Base64 URL-safe format
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash ID", e);
        }
    }
}
