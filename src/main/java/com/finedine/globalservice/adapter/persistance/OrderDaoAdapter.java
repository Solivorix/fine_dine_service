package com.finedine.globalservice.adapter.persistance;

import com.finedine.globalservice.adapter.persistance.mapper.OrderEntityMapper;
import com.finedine.globalservice.adapter.persistance.repository.OrderRepository;
import com.finedine.globalservice.hexagon.application.port.spi.OrderDao;
import com.finedine.globalservice.hexagon.domain.model.OrderModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderDaoAdapter implements OrderDao {
    private final OrderRepository orderRepository;

    @Override
    public OrderModel createOrder(OrderModel orderModel) {
        return Optional.ofNullable(orderModel)
                .map(OrderEntityMapper.INSTANCE::toOrderEntity)
                .map(orderRepository::save)
                .map(OrderEntityMapper.INSTANCE::toOrderModel)
                .orElseThrow(() -> new IllegalArgumentException("orderModel cannot be null"));
    }
}
