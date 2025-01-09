package com.finedine.globalservice.hexagon.application.port.spi;

import com.finedine.globalservice.hexagon.domain.model.AdditionalPricingModel;

import java.util.List;

public interface AdditionalPricingDao {

    AdditionalPricingModel createAdditionalPricing(AdditionalPricingModel additionalPricingModel);

    boolean deleteAdditionalPricing(String id);

    AdditionalPricingModel getAdditionalPricingById(String id);

    List<AdditionalPricingModel> getAllAdditionalPricings();

    AdditionalPricingModel patchAdditionalPricing(String id, AdditionalPricingModel model);

    AdditionalPricingModel updateAdditionalPricing(String id, AdditionalPricingModel model);
}
