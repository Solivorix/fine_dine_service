package com.finedine.globalservice.adapter.persistance;

import com.finedine.globalservice.adapter.persistance.entity.AdditionalPricingEntity;
import com.finedine.globalservice.adapter.persistance.mapper.AdditionalPricingEntityMapper;
import com.finedine.globalservice.adapter.persistance.repository.AdditionalPricingRepository;
import com.finedine.globalservice.hexagon.application.port.spi.AdditionalPricingDao;
import com.finedine.globalservice.hexagon.domain.model.AdditionalPricingModel;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdditionalPricingDaoAdapter implements AdditionalPricingDao {

    private final AdditionalPricingRepository additionalPricingRepository;

    @Override
    public AdditionalPricingModel createAdditionalPricing(AdditionalPricingModel additionalPricingModel) {
        return Optional.ofNullable(additionalPricingModel)
                .map(AdditionalPricingEntityMapper.INSTANCE::toAdditionalPricingEntity)
                .map(additionalPricingRepository::save)
                .map(AdditionalPricingEntityMapper.INSTANCE::toAdditionalPricingModel)
                .orElseThrow(() -> new IllegalArgumentException("AdditionalPricingModel cannot be null"));
    }

    @Override
    public boolean deleteAdditionalPricing(String id) {
        if (additionalPricingRepository.existsById(id)) {
            additionalPricingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public AdditionalPricingModel getAdditionalPricingById(String id) {
        return additionalPricingRepository.findById(id)
                .map(AdditionalPricingEntityMapper.INSTANCE::toAdditionalPricingModel)
                .orElse(null);
    }

    @Override
    public List<AdditionalPricingModel> getAllAdditionalPricings() {
        return additionalPricingRepository.findAll().stream()
                .map(AdditionalPricingEntityMapper.INSTANCE::toAdditionalPricingModel)
                .collect(Collectors.toList());
    }

    @Override
    public AdditionalPricingModel patchAdditionalPricing(String id, AdditionalPricingModel model) {
        AdditionalPricingEntity existingItem = additionalPricingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AdditionalPricing not found"));

        Optional.ofNullable(model.getServiceTypeId()).ifPresent(existingItem::setRestaurantId);
        Optional.ofNullable(model.getPriceName()).ifPresent(existingItem::setPriceName);
        Optional.ofNullable(model.getItemId()).ifPresent(existingItem::setItemId);
        Optional.ofNullable(model.getCharge()).ifPresent(existingItem::setCharge);
        Optional.ofNullable(model.getChargeType()).ifPresent(existingItem::setChargeType);
        Optional.ofNullable(model.getRestaurantId()).ifPresent(existingItem::setRestaurantId);
        Optional.ofNullable(model.getUpdatedBy()).ifPresent(existingItem::setUpdatedBy);

        existingItem = additionalPricingRepository.save(existingItem);
        return AdditionalPricingEntityMapper.INSTANCE.toAdditionalPricingModel(existingItem);
    }

    @Override
    public AdditionalPricingModel updateAdditionalPricing(String id, AdditionalPricingModel model) {
        return additionalPricingRepository.findById(id)
                .map(existingEntity -> {
                    model.setAdditionalPricingId(existingEntity.getAdditionalPricingId());
                    AdditionalPricingEntity entity = additionalPricingRepository.save(AdditionalPricingEntityMapper.INSTANCE.toAdditionalPricingEntity(model));
                    return AdditionalPricingEntityMapper.INSTANCE.toAdditionalPricingModel(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("AdditionalPricing not found with id: " + id));
    }
}
