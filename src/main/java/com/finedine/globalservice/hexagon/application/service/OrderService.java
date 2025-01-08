package com.finedine.globalservice.hexagon.application.service;

import com.finedine.globalservice.hexagon.application.port.api.OrderServicePort;
import com.finedine.globalservice.hexagon.application.port.spi.OrderDao;
import com.finedine.globalservice.hexagon.domain.model.OrderModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService implements OrderServicePort {

    private final OrderDao orderDao;

    @Override
    public OrderModel createOrder(OrderModel orderModel) {
        return orderDao.createOrder(orderModel);
    }

    @Override
    public boolean deleteOrder(String orderId) {
        return orderDao.deleteOrder(orderId);
    }

    @Override
    public List<OrderModel> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public OrderModel getOrderById(String orderId) {
        return orderDao.getOrderById(orderId);
    }

    @Override
    public OrderModel patchOrder(String orderId, OrderModel model) {
        return orderDao.patchOrder(orderId, model);
    }

    @Override
    public OrderModel updateOrder(String orderId, OrderModel model) {
        return orderDao.updateOrder(orderId, model);
    }
}
