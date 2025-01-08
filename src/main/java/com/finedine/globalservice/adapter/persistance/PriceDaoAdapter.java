package com.finedine.globalservice.adapter.persistance;

import com.finedine.globalservice.adapter.persistance.entity.PriceEntity;
import com.finedine.globalservice.adapter.persistance.mapper.PriceEntityMapper;
import com.finedine.globalservice.adapter.persistance.repository.PriceRepository;
import com.finedine.globalservice.hexagon.application.port.spi.PriceDao;
import com.finedine.globalservice.hexagon.domain.model.PriceModel;
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
public class PriceDaoAdapter implements PriceDao {

    private final PriceRepository priceRepository;

    @Override
    public PriceModel createPrice(PriceModel priceModel) {
        return Optional.ofNullable(priceModel)
                .map(PriceEntityMapper.INSTANCE::toPriceEntity)
                .map(priceRepository::save)
                .map(PriceEntityMapper.INSTANCE::toPriceModel)
                .orElseThrow(() -> new IllegalArgumentException("PriceModel cannot be null"));
    }

    @Override
    public boolean deletePrice(String priceId) {
        if (priceRepository.existsById(priceId)) {
            priceRepository.deleteById(priceId);
            return true;
        }
        return false;
    }

    @Override
    public List<PriceModel> getAllPrices() {
        return priceRepository.findAll().stream()
                .map(PriceEntityMapper.INSTANCE::toPriceModel)
                .collect(Collectors.toList());
    }

    @Override
    public PriceModel getPriceById(String priceId) {
        return priceRepository.findById(priceId)
                .map(PriceEntityMapper.INSTANCE::toPriceModel)
                .orElse(null);
    }

    @Transactional
    @Override
    public PriceModel patchPrice(String priceId, PriceModel model) {

        PriceEntity existingItem = priceRepository.findById(priceId)
                .orElseThrow(() -> new EntityNotFoundException("Price not found"));

        Optional.ofNullable(model.getRestaurantId()).ifPresent(existingItem::setRestaurantId);
        Optional.ofNullable(model.getPortionSize()).ifPresent(existingItem::setPortionSize);
        Optional.ofNullable(model.getItemId()).ifPresent(existingItem::setItemId);
        Optional.ofNullable(model.getPrice()).ifPresent(existingItem::setPrice);
        Optional.ofNullable(model.getUpdatedBy()).ifPresent(existingItem::setUpdatedBy);

        existingItem = priceRepository.save(existingItem);
        return PriceEntityMapper.INSTANCE.toPriceModel(existingItem);
    }

    @Override
    public PriceModel updatePrice(String priceId, PriceModel model) {
        return priceRepository.findById(priceId)
                .map(existingEntity -> {
                    model.setPriceId(existingEntity.getPriceId());
                    PriceEntity entity = priceRepository.save(PriceEntityMapper.INSTANCE.toPriceEntity(model));
                    return PriceEntityMapper.INSTANCE.toPriceModel(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Price not found with id: " + priceId));
    }
}
