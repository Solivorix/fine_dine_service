package com.finedine.globalservice.adapter.web.mapper;

import com.finedine.globalservice.hexagon.domain.model.ItemModel;
import com.finedine.globalservice.util.mapper.CommonMappingConfiguration;
import com.finedine.model.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Mapper(config = CommonMappingConfiguration.class)
public interface ItemDtoMapper {

    ItemDtoMapper INSTANCE = Mappers.getMapper(ItemDtoMapper.class);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "offsetToLocal")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "offsetToLocal")
    ItemModel toItemModel(ItemDto itemDto);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localToOffset")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "localToOffset")
    ItemDto toItemDto(ItemModel itemModel);

    List<ItemModel> toItemModelList(List<ItemDto> itemDtoList);

    List<ItemDto> toItemDtoList(List<ItemModel> itemModelList);

    @Named("offsetToLocal")
    default LocalDateTime offsetToLocal(OffsetDateTime offsetDateTime) {
        return offsetDateTime == null ? null : offsetDateTime.toLocalDateTime();
    }

    @Named("localToOffset")
    default OffsetDateTime localToOffset(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.atOffset(OffsetDateTime.now().getOffset());
    }

}
