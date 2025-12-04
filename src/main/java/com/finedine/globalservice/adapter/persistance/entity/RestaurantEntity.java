package com.finedine.globalservice.adapter.persistance.entity;

import com.finedine.globalservice.adapter.util.UniqueHashGenerator;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
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
        this.restId = generateHashId();
    }

    private String generateHashId() {
        String dataToHash = name + gstNo + createdAt.toString(); // Combine key attributes
        return UniqueHashGenerator.generateHash(dataToHash);
    }
}
