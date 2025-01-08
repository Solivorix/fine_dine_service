package com.finedine.globalservice.hexagon.application.port.api;

import com.finedine.globalservice.hexagon.domain.model.OrderModel;

import java.util.List;

public interface OrderServicePort {
    OrderModel createOrder(OrderModel orderModel);

    boolean deleteOrder(String orderId);

    List<OrderModel> getAllOrders();

    OrderModel getOrderById(String orderId);

    OrderModel patchOrder(String orderId, OrderModel model);

    OrderModel updateOrder(String orderId, OrderModel model);
}
