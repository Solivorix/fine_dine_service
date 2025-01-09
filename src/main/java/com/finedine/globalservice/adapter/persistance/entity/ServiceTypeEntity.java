package com.finedine.globalservice.adapter.persistance.entity;

import com.finedine.globalservice.adapter.util.UniqueHashGenerator;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "service_types")
public class ServiceTypeEntity {

    @Id
    @Column(name = "service_type_id")
    private String serviceTypeId;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "restaurant_id")
    private String restaurantId;

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
        this.serviceTypeId = generateHash(this.createdAt.toString());
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        if (this.createdAt != null) {
            this.serviceTypeId = generateHash(this.createdAt.toString());
        }
    }

    private String generateHash(String cratedAt) {
        String uuid = UUID.randomUUID().toString();
        String dataToHash = uuid + "|" + cratedAt;
        return UniqueHashGenerator.generateHash(dataToHash);
    }
}
