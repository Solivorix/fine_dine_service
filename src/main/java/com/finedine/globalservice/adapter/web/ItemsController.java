package com.finedine.globalservice.adapter.web;

import com.finedine.api.ItemsApi;
import com.finedine.globalservice.adapter.web.mapper.ItemDtoMapper;
import com.finedine.globalservice.adapter.web.mapper.PatchItemRequestDtoMapper;
import com.finedine.globalservice.hexagon.application.port.api.ItemServicePort;
import com.finedine.model.ItemDto;
import com.finedine.model.PatchItemRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        return itemServicePort.deleteItem(itemId)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<ItemDto> getItemById(String itemId) {
        return Optional.ofNullable(itemServicePort.getItemById(itemId))
                .map(ItemDtoMapper.INSTANCE::toItemDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<ItemDto>> getItems() {
        return ResponseEntity.ok(
                Optional.ofNullable(itemServicePort.getAllItems())
                        .map(ItemDtoMapper.INSTANCE::toItemDtoList)
                        .orElse(Collections.emptyList())
        );
    }

    @Override
    public ResponseEntity<ItemDto> patchItem(String itemId, PatchItemRequestDto patchItemRequestDto) {
        return Optional.ofNullable(patchItemRequestDto)
                .map(PatchItemRequestDtoMapper.INSTANCE::toItemModel)
                .map(model -> itemServicePort.patchItem(itemId, model))
                .map(ItemDtoMapper.INSTANCE::toItemDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("UserDto cannot be null"));
    }

    @Override
    public ResponseEntity<ItemDto> updateItem(String itemId, ItemDto itemDto) {
        return Optional.ofNullable(itemDto)
                .map(ItemDtoMapper.INSTANCE::toItemModel)
                .map(model -> itemServicePort.updateItem(itemId, model))
                .map(ItemDtoMapper.INSTANCE::toItemDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("ItemDto cannot be null"));
    }
}
