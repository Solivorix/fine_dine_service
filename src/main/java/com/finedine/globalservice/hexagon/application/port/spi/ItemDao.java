package com.finedine.globalservice.hexagon.application.port.spi;

import com.finedine.globalservice.hexagon.domain.model.ItemModel;

import java.util.List;

public interface ItemDao {
    List<ItemModel> createItems(List<ItemModel> itemModelList);

    List<ItemModel> getAllItems();

    ItemModel getItemById(String itemId);

    ItemModel patchItem(String itemId, ItemModel itemModel);

    boolean deleteItem(String itemId);

    ItemModel updateItem(String itemId, ItemModel itemModel);
}
