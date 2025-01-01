package com.finedine.globalservice.adapter.web.mapper;

import com.finedine.globalservice.hexagon.domain.model.RestaurantModel;
import com.finedine.globalservice.util.mapper.CommonMappingConfiguration;
import com.finedine.model.RestaurantDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;


@Mapper(config = CommonMappingConfiguration.class)
public interface RestaurantDtoMapper {

    RestaurantDtoMapper INSTANCE = Mappers.getMapper(RestaurantDtoMapper.class);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "offsetToLocal")
    @Mapping(source = "modifiedAt", target = "modifiedAt", qualifiedByName = "offsetToLocal")
    RestaurantModel toRestaurantModel(RestaurantDto restaurantDto);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localToOffset")
    @Mapping(source = "modifiedAt", target = "modifiedAt", qualifiedByName = "localToOffset")
    RestaurantDto toRestaurantDto(RestaurantModel restaurantModel);

    List<RestaurantModel> toRestaurantModelList(List<RestaurantDto> restaurantDtoList);
    List<RestaurantDto> toRestaurantDtoList(List<RestaurantModel> restaurantModelList);

    @Named("offsetToLocal")
    default LocalDateTime offsetToLocal(OffsetDateTime offsetDateTime) {
        return offsetDateTime == null ? null : offsetDateTime.toLocalDateTime();
    }

    @Named("localToOffset")
    default OffsetDateTime localToOffset(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.atOffset(OffsetDateTime.now().getOffset());
    }
}
