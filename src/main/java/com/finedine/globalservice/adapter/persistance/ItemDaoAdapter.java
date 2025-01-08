package com.finedine.globalservice.adapter.persistance;

import com.finedine.globalservice.adapter.persistance.entity.ItemEntity;
import com.finedine.globalservice.adapter.persistance.mapper.ItemEntityMapper;
import com.finedine.globalservice.adapter.persistance.repository.ItemRepository;
import com.finedine.globalservice.hexagon.application.port.spi.ItemDao;
import com.finedine.globalservice.hexagon.domain.model.ItemModel;
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
public class ItemDaoAdapter implements ItemDao {

    private final ItemRepository itemRepository;

    @Override
    public List<ItemModel> createItems(List<ItemModel> itemModelList) {
        return itemRepository.saveAll(ItemEntityMapper.INSTANCE.toItemEntityList(itemModelList))
                .stream()
                .map(ItemEntityMapper.INSTANCE::toItemModel)
                .toList();
    }

    @Override
    public List<ItemModel> getAllItems() {
        return itemRepository.findAll().stream()
                .map(ItemEntityMapper.INSTANCE::toItemModel)
                .collect(Collectors.toList());
    }

    @Override
    public ItemModel getItemById(String itemId) {
        return itemRepository.findById(itemId)
                .map(ItemEntityMapper.INSTANCE::toItemModel)
                .orElse(null);
    }

    @Transactional
    @Override
    public ItemModel patchItem(String itemId, ItemModel itemModel) {
        ItemEntity existingItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));

        Optional.ofNullable(itemModel.getProductName()).ifPresent(existingItem::setProductName);
        Optional.ofNullable(itemModel.getProductDescription()).ifPresent(existingItem::setProductDescription);
        Optional.ofNullable(itemModel.getItemStatus()).ifPresent(existingItem::setItemStatus);
        Optional.ofNullable(itemModel.getUpdatedBy()).ifPresent(existingItem::setUpdatedBy);

        existingItem = itemRepository.save(existingItem);
        return ItemEntityMapper.INSTANCE.toItemModel(existingItem);
    }

    @Override
    public boolean deleteItem(String itemId) {
        if (itemRepository.existsById(itemId)) {
            itemRepository.deleteById(itemId);
            return true;
        }
        return false;
    }

    @Override
    public ItemModel updateItem(String itemId, ItemModel itemModel) {
        return itemRepository.findById(itemId)
                .map(existingEntity -> {
                    itemModel.setItemId(existingEntity.getItemId());
                    ItemEntity entity = itemRepository.save(ItemEntityMapper.INSTANCE.toItemEntity(itemModel));
                    return ItemEntityMapper.INSTANCE.toItemModel(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + itemId));
    }
}
