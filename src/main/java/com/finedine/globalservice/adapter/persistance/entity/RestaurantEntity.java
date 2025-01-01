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
@Table(name = "restaurant")
public class RestaurantEntity {

    @Id
    private String restId;
    private String name;
    private String gstNo;
    @Lob
    private String address;
    private String primaryEmailId;
    private String primaryContactNumber;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String createdBy;
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
