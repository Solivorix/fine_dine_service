package com.finedine.globalservice.adapter.web.mapper;

import com.finedine.globalservice.hexagon.domain.model.OrderModel;
import com.finedine.globalservice.util.mapper.CommonMappingConfiguration;
import com.finedine.model.OrderDto;
import com.finedine.model.OrderInputDto;
import com.finedine.model.OrderPartialInputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = CommonMappingConfiguration.class)
public interface OrderDtoMapper {

    OrderDtoMapper INSTANCE = Mappers.getMapper(OrderDtoMapper.class);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "offsetToLocal")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "offsetToLocal")
    OrderModel toOrderModel(OrderDto orderDto);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localToOffset")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "localToOffset")
    OrderDto toOrderDto(OrderModel orderModel);

    List<OrderModel> toOrderModelList(List<OrderDto> orderDtoList);

    List<OrderDto> toOrderDtoList(List<OrderModel> orderModelList);

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    OrderModel toOrderModel(OrderInputDto orderInputDto);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    OrderModel toOrderModel(OrderPartialInputDto orderPartialInputDto);

}
