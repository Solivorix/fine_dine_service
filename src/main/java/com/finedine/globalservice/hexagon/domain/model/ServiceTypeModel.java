package com.finedine.globalservice.hexagon.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ServiceTypeModel {

    private String serviceTypeId;

    private String typeName;

    private String restaurantId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String createdBy;

    private String updatedBy;
}
