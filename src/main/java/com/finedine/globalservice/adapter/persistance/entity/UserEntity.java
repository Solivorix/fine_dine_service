package com.finedine.globalservice.adapter.persistance.entity;

import com.finedine.globalservice.adapter.util.UniqueHashGenerator;
import com.finedine.globalservice.hexagon.domain.model.UserModel;
import jakarta.persistence.*;
import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "restaurant_id")
    private String restaurantId;

    @Column(name = "user_name")
    private String userName;

    private String email;

    private String password;

    @Column(name = "contact_number")
    private String contactNumber;

    private String role;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String modifiedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.email != null) {
            this.userId = hashEmail(this.email);
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        if (this.email != null) {
            this.userId = hashEmail(this.email); // Ensure hash is updated if email changes
        }
    }
    private String hashEmail(String email) {
        return UniqueHashGenerator.generateHash(email);
    }

}
