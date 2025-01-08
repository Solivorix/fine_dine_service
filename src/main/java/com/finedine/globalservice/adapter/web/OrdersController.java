package com.finedine.globalservice.adapter.web;

import com.finedine.api.OrdersApi;
import com.finedine.globalservice.adapter.web.mapper.OrderDtoMapper;
import com.finedine.globalservice.hexagon.application.port.api.OrderServicePort;
import com.finedine.model.OrderDto;
import com.finedine.model.OrderInputDto;
import com.finedine.model.OrderPartialInputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrdersController implements OrdersApi {

    private final OrderServicePort orderServicePort;

    @Override
    public ResponseEntity<OrderDto> createOrder(OrderInputDto orderInputDto) {
        return Optional.ofNullable(orderInputDto)
                .map(OrderDtoMapper.INSTANCE::toOrderModel)
                .map(orderServicePort::createOrder)
                .map(OrderDtoMapper.INSTANCE::toOrderDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("orderInputDto cannot be null"));
    }

    @Override
    public ResponseEntity<Void> deleteOrder(String orderId) {
        return orderServicePort.deleteOrder(orderId)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(
                Optional.ofNullable(orderServicePort.getAllOrders())
                        .map(OrderDtoMapper.INSTANCE::toOrderDtoList)
                        .orElse(Collections.emptyList())
        );
    }

    @Override
    public ResponseEntity<OrderDto> getOrderById(String orderId) {
        return Optional.ofNullable(orderServicePort.getOrderById(orderId))
                .map(OrderDtoMapper.INSTANCE::toOrderDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<OrderDto> patchOrder(String orderId, OrderPartialInputDto orderPartialInputDto) {
        return Optional.ofNullable(orderPartialInputDto)
                .map(OrderDtoMapper.INSTANCE::toOrderModel)
                .map(model -> orderServicePort.patchOrder(orderId, model))
                .map(OrderDtoMapper.INSTANCE::toOrderDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("OrderPartialInputDto cannot be null"));
    }

    @Override
    public ResponseEntity<OrderDto> updateOrder(String orderId, OrderInputDto orderInputDto) {
        return Optional.ofNullable(orderInputDto)
                .map(OrderDtoMapper.INSTANCE::toOrderModel)
                .map(model -> orderServicePort.updateOrder(orderId, model))
                .map(OrderDtoMapper.INSTANCE::toOrderDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("OrderInputDto cannot be null"));
    }
}
