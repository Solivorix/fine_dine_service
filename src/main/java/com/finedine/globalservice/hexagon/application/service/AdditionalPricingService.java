package com.finedine.globalservice.hexagon.application.service;

import com.finedine.globalservice.hexagon.application.port.api.AdditionalPricingServicePort;
import com.finedine.globalservice.hexagon.application.port.spi.AdditionalPricingDao;
import com.finedine.globalservice.hexagon.domain.model.AdditionalPricingModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdditionalPricingService implements AdditionalPricingServicePort {

    private final AdditionalPricingDao additionalPricingDao;

    @Override
    public AdditionalPricingModel createAdditionalPricing(AdditionalPricingModel additionalPricingModel) {
        return additionalPricingDao.createAdditionalPricing(additionalPricingModel);
    }

    @Override
    public boolean deleteAdditionalPricing(String id) {
        return additionalPricingDao.deleteAdditionalPricing(id);
    }

    @Override
    public AdditionalPricingModel getAdditionalPricingById(String id) {
        return additionalPricingDao.getAdditionalPricingById(id);
    }

    @Override
    public List<AdditionalPricingModel> getAllAdditionalPricings() {
        return additionalPricingDao.getAllAdditionalPricings();
    }

    @Override
    public AdditionalPricingModel patchAdditionalPricing(String id, AdditionalPricingModel model) {
        return additionalPricingDao.patchAdditionalPricing(id,model);
    }

    @Override
    public AdditionalPricingModel updateAdditionalPricing(String id, AdditionalPricingModel model) {
        return additionalPricingDao.updateAdditionalPricing(id,model);
    }
}
