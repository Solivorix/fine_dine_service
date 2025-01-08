package com.finedine.globalservice.adapter.persistance.mapper;

import com.finedine.globalservice.adapter.persistance.entity.PriceEntity;
import com.finedine.globalservice.hexagon.domain.model.PriceModel;
import com.finedine.globalservice.util.mapper.CommonMappingConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = CommonMappingConfiguration.class)
public interface PriceEntityMapper {

    PriceEntityMapper INSTANCE = Mappers.getMapper(PriceEntityMapper.class);

    PriceEntity toPriceEntity(PriceModel priceModel);
    PriceModel toPriceModel(PriceEntity priceEntity);

    List<PriceEntity> toPriceEntityList(List<PriceModel> priceModelList);
    List<PriceModel> toPriceModelList(List<PriceEntity> priceEntityList);
}
