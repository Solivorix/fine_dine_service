package com.finedine.globalservice.adapter.persistance;

import com.finedine.globalservice.adapter.persistance.mapper.ItemEntityMapper;
import com.finedine.globalservice.adapter.persistance.repository.ItemRepository;
import com.finedine.globalservice.hexagon.application.port.spi.ItemDao;
import com.finedine.globalservice.hexagon.domain.model.ItemModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
