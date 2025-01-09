package com.finedine.globalservice.adapter.web;

import com.finedine.api.AdditionalPricingsApi;
import com.finedine.model.AdditionalPricingDto;
import com.finedine.model.AdditionalPricingInputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AdditionalPricingController implements AdditionalPricingsApi {
    @Override
    public ResponseEntity<AdditionalPricingDto> createAdditionalPricing(AdditionalPricingInputDto additionalPricingInputDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteAdditionalPricing(String id) {
        return null;
    }

    @Override
    public ResponseEntity<AdditionalPricingDto> getAdditionalPricingById(String id) {
        return null;
    }

    @Override
    public ResponseEntity<List<AdditionalPricingDto>> getAllAdditionalPricings() {
        return null;
    }

    @Override
    public ResponseEntity<AdditionalPricingDto> partiallyUpdateAdditionalPricing(String id, AdditionalPricingInputDto additionalPricingInputDto) {
        return null;
    }

    @Override
    public ResponseEntity<AdditionalPricingDto> updateAdditionalPricing(String id, AdditionalPricingInputDto additionalPricingInputDto) {
        return null;
    }
}
