package com.finedine.globalservice.adapter.web.mapper;

import com.finedine.globalservice.hexagon.domain.model.ServiceTypeModel;
import com.finedine.globalservice.util.mapper.CommonMappingConfiguration;
import com.finedine.model.ServiceTypeDto;
import com.finedine.model.ServiceTypePartialDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(config = CommonMappingConfiguration.class)
public interface ServiceTypeDtoMapper {

    ServiceTypeDtoMapper INSTANCE = Mappers.getMapper(ServiceTypeDtoMapper.class);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "offsetToLocal")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "offsetToLocal")
    ServiceTypeModel toServiceTypeModel(ServiceTypeDto serviceTypeDto);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localToOffset")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "localToOffset")
    ServiceTypeDto toServiceTypeDto(ServiceTypeModel serviceTypeModel);

    List<ServiceTypeModel> toServiceTypeModelList(List<ServiceTypeDto> serviceTypeDtoList);

    List<ServiceTypeDto> toServiceTypeDtoList(List<ServiceTypeModel> serviceTypeModelList);

    ServiceTypeModel toServiceTypeModel(ServiceTypePartialDto serviceTypePartialDto);

}
