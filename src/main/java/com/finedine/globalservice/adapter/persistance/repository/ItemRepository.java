package com.finedine.globalservice.adapter.persistance.repository;

import com.finedine.globalservice.adapter.persistance.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity,String> {
}
