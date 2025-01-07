package com.finedine.globalservice.adapter.persistance.repository;

import com.finedine.globalservice.adapter.persistance.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,String> {
}
