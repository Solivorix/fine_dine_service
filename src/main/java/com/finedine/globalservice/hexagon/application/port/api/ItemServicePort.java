package com.finedine.globalservice.hexagon.application.port.api;

import com.finedine.globalservice.hexagon.domain.model.ItemModel;

import java.util.List;

public interface ItemServicePort {
    List<ItemModel> createItems(List<ItemModel> itemModelList);
}
