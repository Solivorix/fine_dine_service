package com.finedine.globalservice.adapter.web;

import com.finedine.api.AdditionalPricingsApi;
import com.finedine.globalservice.adapter.web.mapper.AdditionalPricingDtoMapper;
import com.finedine.globalservice.hexagon.application.port.api.AdditionalPricingServicePort;
import com.finedine.model.AdditionalPricingDto;
import com.finedine.model.AdditionalPricingInputDto;
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
public class AdditionalPricingController implements AdditionalPricingsApi {

    private final AdditionalPricingServicePort additionalPricingServicePort;

    @Override
    public ResponseEntity<AdditionalPricingDto> createAdditionalPricing(AdditionalPricingInputDto additionalPricingInputDto) {
        return ResponseEntity.ok(
                AdditionalPricingDtoMapper.INSTANCE.toAdditionalPricingDto(
                        additionalPricingServicePort.createAdditionalPricing(AdditionalPricingDtoMapper.INSTANCE.toAdditionalPricingModel(additionalPricingInputDto))
                )
        );
    }

    @Override
    public ResponseEntity<Void> deleteAdditionalPricing(String id) {
        return additionalPricingServicePort.deleteAdditionalPricing(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<AdditionalPricingDto> getAdditionalPricingById(String id) {
        return Optional.ofNullable(additionalPricingServicePort.getAdditionalPricingById(id))
                .map(AdditionalPricingDtoMapper.INSTANCE::toAdditionalPricingDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<AdditionalPricingDto>> getAllAdditionalPricings() {
        return ResponseEntity.ok(
                Optional.ofNullable(additionalPricingServicePort.getAllAdditionalPricings())
                        .map(AdditionalPricingDtoMapper.INSTANCE::toAdditionalPricingDtoList)
                        .orElse(Collections.emptyList())
        );
    }

    @Override
    public ResponseEntity<AdditionalPricingDto> partiallyUpdateAdditionalPricing(String id, AdditionalPricingInputDto additionalPricingInputDto) {
        return Optional.ofNullable(additionalPricingInputDto)
                .map(AdditionalPricingDtoMapper.INSTANCE::toAdditionalPricingModel)
                .map(model -> additionalPricingServicePort.patchAdditionalPricing(id, model))
                .map(AdditionalPricingDtoMapper.INSTANCE::toAdditionalPricingDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("AdditionalPricingInputDto cannot be null"));
    }


    @Override
    public ResponseEntity<AdditionalPricingDto> updateAdditionalPricing(String id, AdditionalPricingInputDto additionalPricingInputDto) {
        return Optional.ofNullable(additionalPricingInputDto)
                .map(AdditionalPricingDtoMapper.INSTANCE::toAdditionalPricingModel)
                .map(model -> additionalPricingServicePort.updateAdditionalPricing(id, model))
                .map(AdditionalPricingDtoMapper.INSTANCE::toAdditionalPricingDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("AdditionalPricingInputDto cannot be null"));
    }
}
