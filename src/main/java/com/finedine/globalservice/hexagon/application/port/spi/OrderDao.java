package com.finedine.globalservice.hexagon.application.port.spi;

import com.finedine.globalservice.hexagon.domain.model.OrderModel;

import java.util.List;

public interface OrderDao {
    OrderModel createOrder(OrderModel orderModel);

    OrderModel patchOrder(String orderId, OrderModel model);

    OrderModel getOrderById(String orderId);

    List<OrderModel> getAllOrders();

    boolean deleteOrder(String orderId);

    OrderModel updateOrder(String orderId, OrderModel model);
}
