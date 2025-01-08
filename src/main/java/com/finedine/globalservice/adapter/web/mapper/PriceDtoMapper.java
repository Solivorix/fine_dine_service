package com.finedine.globalservice.adapter.web.mapper;

import com.finedine.globalservice.hexagon.domain.model.PriceModel;
import com.finedine.globalservice.util.mapper.CommonMappingConfiguration;
import com.finedine.model.PriceDto;
import com.finedine.model.PricePartialDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Mapper(config = CommonMappingConfiguration.class)
public interface PriceDtoMapper {

    PriceDtoMapper INSTANCE = Mappers.getMapper(PriceDtoMapper.class);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "offsetToLocal")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "offsetToLocal")
    PriceModel toPriceModel(PriceDto priceDto);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localToOffset")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "localToOffset")
    PriceDto toPriceDto(PriceModel priceModel);

    List<PriceModel> toPriceModelList(List<PriceDto>priceDtoList);
    List<PriceDto>  toPriceDtoList(List<PriceModel> priceModelList);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "priceId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    PriceModel toPriceModel(PricePartialDto pricePartialDto);

    @Named("offsetToLocal")
    default LocalDateTime offsetToLocal(OffsetDateTime offsetDateTime) {
        return offsetDateTime == null ? null : offsetDateTime.toLocalDateTime();
    }

    @Named("localToOffset")
    default OffsetDateTime localToOffset(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.atOffset(OffsetDateTime.now().getOffset());
    }


}
