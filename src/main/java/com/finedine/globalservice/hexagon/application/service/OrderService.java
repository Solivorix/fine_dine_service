package com.finedine.globalservice.hexagon.application.service;

import com.finedine.globalservice.hexagon.application.port.api.OrderServicePort;
import com.finedine.globalservice.hexagon.application.port.spi.OrderDao;
import com.finedine.globalservice.hexagon.domain.model.OrderModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService implements OrderServicePort {

    private final OrderDao orderDao;

    @Override
    public OrderModel createOrder(OrderModel orderModel) {
        return orderDao.createOrder(orderModel);
    }
}
