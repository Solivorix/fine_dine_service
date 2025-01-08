package com.finedine.globalservice.hexagon.application.port.spi;

import com.finedine.globalservice.hexagon.domain.model.PriceModel;

import java.util.List;

public interface PriceDao {

    PriceModel createPrice(PriceModel priceModel);

    boolean deletePrice(String priceId);

    List<PriceModel> getAllPrices();

    PriceModel getPriceById(String priceId);

    PriceModel patchPrice(String priceId, PriceModel model);

    PriceModel updatePrice(String priceId, PriceModel model);
}
