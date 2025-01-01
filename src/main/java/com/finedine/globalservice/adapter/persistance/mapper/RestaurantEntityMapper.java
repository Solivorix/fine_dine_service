package com.finedine.globalservice.adapter.persistance.mapper;

import com.finedine.globalservice.adapter.persistance.entity.RestaurantEntity;
import com.finedine.globalservice.hexagon.domain.model.RestaurantModel;
import com.finedine.globalservice.util.mapper.CommonMappingConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = CommonMappingConfiguration.class)
public interface RestaurantEntityMapper {

    RestaurantEntityMapper INSTANCE = Mappers.getMapper(RestaurantEntityMapper.class);

    RestaurantEntity toRestaurantEntity(RestaurantModel restaurantModel);

    RestaurantModel toRestaurantModel(RestaurantEntity restaurantEntity);

    List<RestaurantEntity> toRestaurantEntityList(List<RestaurantModel> restaurantModels);

    List<RestaurantModel> toRestaurantModelList(List<RestaurantEntity> restaurantEntities);
}
