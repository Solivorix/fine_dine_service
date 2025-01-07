package com.finedine.globalservice.adapter.web.mapper;

import com.finedine.globalservice.hexagon.domain.model.ItemModel;
import com.finedine.globalservice.util.mapper.CommonMappingConfiguration;
import com.finedine.model.PatchItemRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = CommonMappingConfiguration.class)
public interface PatchItemRequestDtoMapper {
    PatchItemRequestDtoMapper INSTANCE = Mappers.getMapper(PatchItemRequestDtoMapper.class);

    ItemModel toItemModel(PatchItemRequestDto patchItemRequestDto);
}
