package com.finedine.globalservice.adapter.persistance.entity;

import com.finedine.globalservice.adapter.util.UniqueHashGenerator;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "items")
public class ItemEntity {

    @Id
    @Column(name = "item_id")
    private String itemId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "restaurant_id")
    private String restaurantId;

    @Column(name = "item_status")
    private String itemStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.productId != null) {
            this.itemId = generateHash(this.productId);
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        if (this.productId != null) {
            this.itemId = generateHash(this.productId); // Ensure hash is updated if productId changes
        }
    }
    private String generateHash(String productId) {
        String dataToHash = productId + restaurantId + createdAt;
        return UniqueHashGenerator.generateHash(dataToHash);
    }
}
