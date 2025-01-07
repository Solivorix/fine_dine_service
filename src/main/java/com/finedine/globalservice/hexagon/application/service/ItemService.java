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
}
