package com.finedine.globalservice.hexagon.application.port.spi;

import com.finedine.globalservice.hexagon.domain.model.OrderModel;

public interface OrderDao {
    OrderModel createOrder(OrderModel orderModel);
}
