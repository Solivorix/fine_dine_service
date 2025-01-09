package com.finedine.globalservice.adapter.web.mapper;

import com.finedine.globalservice.hexagon.domain.model.AdditionalPricingModel;
import com.finedine.globalservice.util.mapper.CommonMappingConfiguration;
import com.finedine.model.AdditionalPricingDto;
import com.finedine.model.AdditionalPricingInputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = CommonMappingConfiguration.class)
public interface AdditionalPricingDtoMapper {

    AdditionalPricingDtoMapper INSTANCE = Mappers.getMapper(AdditionalPricingDtoMapper.class);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "offsetToLocal")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "offsetToLocal")
    AdditionalPricingModel toAdditionalPricingModel(AdditionalPricingDto additionalPricingDto);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localToOffset")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "localToOffset")
    AdditionalPricingDto toAdditionalPricingDto(AdditionalPricingModel additionalPricingModel);

    List<AdditionalPricingModel> toAdditionalPricingModelList(List<AdditionalPricingDto> additionalPricingDtoList);
    List<AdditionalPricingDto> toAdditionalPricingDtoList(List<AdditionalPricingModel> additionalPricingModelList);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "additionalPricingId", ignore = true)
    AdditionalPricingModel toAdditionalPricingModel(AdditionalPricingInputDto additionalPricingInputDto);
}
