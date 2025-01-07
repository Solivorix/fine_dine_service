package com.finedine.globalservice.adapter.persistance.mapper;

import com.finedine.globalservice.adapter.persistance.entity.OrderEntity;
import com.finedine.globalservice.hexagon.domain.model.OrderModel;
import com.finedine.globalservice.util.mapper.CommonMappingConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = CommonMappingConfiguration.class)
public interface OrderEntityMapper {

    OrderEntityMapper INSTANCE = Mappers.getMapper(OrderEntityMapper.class);

    OrderEntity toOrderEntity(OrderModel orderModel);
    OrderModel toOrderModel(OrderEntity orderEntity);
}
