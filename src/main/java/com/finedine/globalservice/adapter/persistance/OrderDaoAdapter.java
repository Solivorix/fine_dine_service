package com.finedine.globalservice.adapter.persistance;

import com.finedine.globalservice.adapter.persistance.entity.OrderEntity;
import com.finedine.globalservice.adapter.persistance.mapper.OrderEntityMapper;
import com.finedine.globalservice.adapter.persistance.repository.OrderRepository;
import com.finedine.globalservice.hexagon.application.port.spi.OrderDao;
import com.finedine.globalservice.hexagon.domain.model.OrderModel;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional
    @Override
    public OrderModel patchOrder(String orderId, OrderModel model) {
        OrderEntity existingItem = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        Optional.ofNullable(model.getRestaurantId()).ifPresent(existingItem::setRestaurantId);
        Optional.ofNullable(model.getProductId()).ifPresent(existingItem::setProductId);
        Optional.ofNullable(model.getTableNumber()).ifPresent(existingItem::setTableNumber);
        Optional.ofNullable(model.getUpdatedBy()).ifPresent(existingItem::setUpdatedBy);

        existingItem = orderRepository.save(existingItem);
        return OrderEntityMapper.INSTANCE.toOrderModel(existingItem);
    }

    @Override
    public OrderModel getOrderById(String orderId) {
        return orderRepository.findById(orderId)
                .map(OrderEntityMapper.INSTANCE::toOrderModel)
                .orElse(null);
    }

    @Override
    public List<OrderModel> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderEntityMapper.INSTANCE::toOrderModel)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteOrder(String orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
            return true;
        }
        return false;
    }

    @Override
    public OrderModel updateOrder(String orderId, OrderModel model) {
        return orderRepository.findById(orderId)
                .map(existingEntity -> {
                    model.setOrderId(existingEntity.getOrderId());
                    OrderEntity entity = orderRepository.save(OrderEntityMapper.INSTANCE.toOrderEntity(model));
                    return OrderEntityMapper.INSTANCE.toOrderModel(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
    }
}
