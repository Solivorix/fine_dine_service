package com.finedine.globalservice.adapter.persistance.mapper;

import com.finedine.globalservice.adapter.persistance.entity.AdditionalPricingEntity;
import com.finedine.globalservice.hexagon.domain.model.AdditionalPricingModel;
import com.finedine.globalservice.util.mapper.CommonMappingConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = CommonMappingConfiguration.class)
public interface AdditionalPricingEntityMapper {

    AdditionalPricingEntityMapper INSTANCE = Mappers.getMapper(AdditionalPricingEntityMapper.class);

    AdditionalPricingEntity toAdditionalPricingEntity(AdditionalPricingModel additionalPricingModel);
    AdditionalPricingModel toAdditionalPricingModel(AdditionalPricingEntity additionalPricingEntity);
}
