package com.finedine.globalservice.hexagon.application.port.api;

import com.finedine.globalservice.hexagon.domain.model.ItemModel;

import java.util.List;

public interface ItemServicePort {
    List<ItemModel> createItems(List<ItemModel> itemModelList);

    List<ItemModel> getAllItems();

    ItemModel getItemById(String itemId);

    ItemModel patchItem(String itemId, ItemModel itemModel);

    boolean deleteItem(String itemId);

    ItemModel updateItem(String itemId, ItemModel model);
}
