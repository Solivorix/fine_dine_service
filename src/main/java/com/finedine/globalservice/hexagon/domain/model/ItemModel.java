package com.finedine.globalservice.hexagon.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ItemModel {

    private String itemId;

    private String productId;

    private String productName;

    private String productDescription;

    private String restaurantId;

    private String itemStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String createdBy;

    private String updatedBy;
}
