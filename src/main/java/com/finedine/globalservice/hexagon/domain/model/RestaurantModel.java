package com.finedine.globalservice.hexagon.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RestaurantModel {

    private String restId;
    private String name;
    private String gstNo;
    private String primaryEmailId;
    private String primaryContactNumber;
    private String address;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
