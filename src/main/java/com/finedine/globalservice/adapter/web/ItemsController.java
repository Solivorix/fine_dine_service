package com.finedine.globalservice.adapter.web;

import com.finedine.api.ItemsApi;
import com.finedine.globalservice.adapter.web.mapper.ItemDtoMapper;
import com.finedine.globalservice.hexagon.application.port.api.ItemServicePort;
import com.finedine.model.ItemDto;
import com.finedine.model.PatchItemRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ItemsController implements ItemsApi {

    private final ItemServicePort itemServicePort;


    @Override
    public ResponseEntity<List<ItemDto>> createItems(List<ItemDto> itemDto) {
        return ResponseEntity.ok(
                ItemDtoMapper.INSTANCE.toItemDtoList(
                        itemServicePort.createItems(ItemDtoMapper.INSTANCE.toItemModelList(itemDto))
                )
        );
    }

    @Override
    public ResponseEntity<Void> deleteItem(String itemId) {
        return null;
    }

    @Override
    public ResponseEntity<ItemDto> getItemById(String itemId) {
        return null;
    }

    @Override
    public ResponseEntity<List<ItemDto>> getItems() {
        return null;
    }

    @Override
    public ResponseEntity<ItemDto> patchItem(String itemId, PatchItemRequestDto patchItemRequestDto) {
        return null;
    }

    @Override
    public ResponseEntity<ItemDto> updateItem(String itemId, ItemDto itemDto) {
        return null;
    }
}
