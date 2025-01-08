package com.finedine.globalservice.adapter.web;

import com.finedine.api.PricesApi;
import com.finedine.globalservice.adapter.web.mapper.PriceDtoMapper;
import com.finedine.globalservice.hexagon.application.port.api.PriceServicePort;
import com.finedine.model.PriceDto;
import com.finedine.model.PricePartialDto;
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
public class PricesController implements PricesApi {

    private final PriceServicePort priceServicePort;

    @Override
    public ResponseEntity<PriceDto> createPrice(PriceDto priceDto) {
        return Optional.ofNullable(priceDto)
                .map(PriceDtoMapper.INSTANCE::toPriceModel)
                .map(priceServicePort::createPrice)
                .map(PriceDtoMapper.INSTANCE::toPriceDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("PriceDto cannot be null"));
    }

    @Override
    public ResponseEntity<Void> deletePriceById(String priceId) {
        return priceServicePort.deletePrice(priceId)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<PriceDto>> getAllPrices() {
        return ResponseEntity.ok(
                Optional.ofNullable(priceServicePort.getAllPrices())
                        .map(PriceDtoMapper.INSTANCE::toPriceDtoList)
                        .orElse(Collections.emptyList())
        );
    }

    @Override
    public ResponseEntity<PriceDto> getPriceById(String priceId) {
        return Optional.ofNullable(priceServicePort.getPriceById(priceId))
                .map(PriceDtoMapper.INSTANCE::toPriceDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<PriceDto> partiallyUpdatePriceById(String priceId, PricePartialDto pricePartialDto) {
        return Optional.ofNullable(pricePartialDto)
                .map(PriceDtoMapper.INSTANCE::toPriceModel)
                .map(model -> priceServicePort.patchPrice(priceId, model))
                .map(PriceDtoMapper.INSTANCE::toPriceDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("PricePartialDto cannot be null"));
    }

    @Override
    public ResponseEntity<PriceDto> updatePriceById(String priceId, PriceDto priceDto) {
        return Optional.ofNullable(priceDto)
                .map(PriceDtoMapper.INSTANCE::toPriceModel)
                .map(model -> priceServicePort.updatePrice(priceId, model))
                .map(PriceDtoMapper.INSTANCE::toPriceDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("PriceDto cannot be null"));
    }
}
