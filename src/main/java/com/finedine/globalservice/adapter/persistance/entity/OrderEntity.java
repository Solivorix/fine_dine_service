package com.finedine.globalservice.adapter.persistance.entity;

import com.finedine.globalservice.adapter.util.UniqueHashGenerator;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "orders")
public class OrderEntity {

    @Id
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "restaurant_id")
    private String restaurantId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "table_number")
    private Integer tableNumber;

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
        this.orderId = generateHash(this.createdAt.toString());
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        if (this.createdAt != null) {
            this.orderId = generateHash(this.createdAt.toString());
        }
    }
    private String generateHash(String cratedAt) {
        String uuid = UUID.randomUUID().toString();
        String dataToHash = uuid + "|" + cratedAt;
        return UniqueHashGenerator.generateHash(dataToHash);
    }
}
