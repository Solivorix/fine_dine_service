package com.finedine.globalservice.hexagon.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderModel {

    private String orderId;

    private String restaurantId;

    private String productId;

    private Integer tableNumber;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String createdBy;

    private String updatedBy;
}
