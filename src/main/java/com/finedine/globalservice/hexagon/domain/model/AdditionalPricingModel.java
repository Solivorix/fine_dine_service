package com.finedine.globalservice.hexagon.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class AdditionalPricingModel {

    private String additionalPricingId;

    private String serviceTypeId;

    private String priceName;

    private String itemId;

    private BigDecimal charge;

    private String chargeType;

    private String restaurantId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String createdBy;

    private String updatedBy;
}
