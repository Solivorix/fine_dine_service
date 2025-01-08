package com.finedine.globalservice.hexagon.application.service;

import com.finedine.globalservice.hexagon.application.port.api.PriceServicePort;
import com.finedine.globalservice.hexagon.application.port.spi.PriceDao;
import com.finedine.globalservice.hexagon.domain.model.PriceModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PriceService implements PriceServicePort {

    private final PriceDao priceDao;

    @Override
    public PriceModel createPrice(PriceModel priceModel) {
        return priceDao.createPrice(priceModel);
    }

    @Override
    public boolean deletePrice(String priceId) {
        return priceDao.deletePrice(priceId);
    }

    @Override
    public List<PriceModel> getAllPrices() {
        return priceDao.getAllPrices();
    }

    @Override
    public PriceModel getPriceById(String priceId) {
        return priceDao.getPriceById(priceId);
    }

    @Override
    public PriceModel patchPrice(String priceId, PriceModel model) {
        return priceDao.patchPrice(priceId, model);
    }

    @Override
    public PriceModel updatePrice(String priceId, PriceModel model) {
        return priceDao.updatePrice(priceId, model);
    }
}
