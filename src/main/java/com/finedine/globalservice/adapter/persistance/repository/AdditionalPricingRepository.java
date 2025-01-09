package com.finedine.globalservice.adapter.persistance.repository;

import com.finedine.globalservice.adapter.persistance.entity.AdditionalPricingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalPricingRepository extends JpaRepository<AdditionalPricingEntity,String> {
}
