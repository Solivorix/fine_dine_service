package com.finedine.globalservice.hexagon.application.service;

import com.finedine.globalservice.hexagon.application.port.api.ItemServicePort;
import com.finedine.globalservice.hexagon.application.port.spi.ItemDao;
import com.finedine.globalservice.hexagon.domain.model.ItemModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ItemService implements ItemServicePort {

    private final ItemDao itemDao;

    @Override
    public List<ItemModel> createItems(List<ItemModel> itemModelList) {
        return itemDao.createItems(itemModelList);
    }

    @Override
    public List<ItemModel> getAllItems() {
        return itemDao.getAllItems();
    }

    @Override
    public ItemModel getItemById(String itemId) {
        return itemDao.getItemById(itemId);
    }

    @Override
    public ItemModel patchItem(String itemId, ItemModel itemModel) {
        return itemDao.patchItem(itemId, itemModel);
    }

    @Override
    public boolean deleteItem(String itemId) {
        return itemDao.deleteItem(itemId);
    }

    @Override
    public ItemModel updateItem(String itemId, ItemModel itemModel) {
        return itemDao.updateItem(itemId, itemModel);
    }
}
