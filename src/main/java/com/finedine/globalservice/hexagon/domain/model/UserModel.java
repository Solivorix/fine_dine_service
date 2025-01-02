package com.finedine.globalservice.hexagon.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserModel {
    private String userId;
    private String restaurantId;
    private String userName;
    private String email;
    private String password;
    private String passwordSalt;
    private String contactNumber;
    private String role;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
