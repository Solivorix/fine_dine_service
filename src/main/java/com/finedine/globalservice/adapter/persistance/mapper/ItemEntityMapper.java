package com.finedine.globalservice.adapter.persistance.mapper;

import com.finedine.globalservice.adapter.persistance.entity.ItemEntity;
import com.finedine.globalservice.hexagon.domain.model.ItemModel;
import com.finedine.globalservice.util.mapper.CommonMappingConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = CommonMappingConfiguration.class)
public interface ItemEntityMapper {

    ItemEntityMapper INSTANCE = Mappers.getMapper(ItemEntityMapper.class);

    ItemEntity toItemEntity(ItemModel itemModel);
    ItemModel toItemModel(ItemEntity itemEntity);

    List<ItemEntity> toItemEntityList(List<ItemModel> itemModelList);
    List<ItemModel> toItemModelList(List<ItemEntity> itemEntityList);

}
