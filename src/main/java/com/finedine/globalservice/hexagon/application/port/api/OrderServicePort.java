package com.finedine.globalservice.hexagon.application.port.api;

import com.finedine.globalservice.hexagon.domain.model.OrderModel;

public interface OrderServicePort {
    OrderModel createOrder(OrderModel orderModel);
}
