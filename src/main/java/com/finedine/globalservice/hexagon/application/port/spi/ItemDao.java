package com.finedine.globalservice.hexagon.application.port.spi;

import com.finedine.globalservice.hexagon.domain.model.ItemModel;

import java.util.List;

public interface ItemDao {
    List<ItemModel> createItems(List<ItemModel> itemModelList);
}
